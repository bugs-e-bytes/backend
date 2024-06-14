package br.com.creativeexperience.book_now.accommodation.services;

import br.com.creativeexperience.book_now.accommodation.dto.AccommodationRequest;
import br.com.creativeexperience.book_now.accommodation.dto.AccommodationResponse;
import br.com.creativeexperience.book_now.accommodation.entities.Accommodation;
import br.com.creativeexperience.book_now.accommodation.mapping.AccommodationMapper;
import br.com.creativeexperience.book_now.accommodation.repositories.AccommodationRepository;
import br.com.creativeexperience.book_now.exceptions.runtimes.AccommodationNotFoundException;
import br.com.creativeexperience.book_now.exceptions.runtimes.AccommodationOwnershipException;
import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;

    @Transactional
    public AccommodationResponse createAccommodationForUser(AccommodationRequest request, String username) {
        // Recuperar o usuário com base no nome de usuário fornecido
        User user = userRepository.findByEmail(username).orElseThrow();

        // Mapear a solicitação de acomodação para uma entidade de acomodação
        Accommodation accommodation = AccommodationMapper.INSTANCE.toAccommodation(request);

        // Associar a acomodação ao usuário recuperado
        accommodation.setUser(user);

        // Salvar a acomodação no banco de dados
        accommodation = accommodationRepository.save(accommodation);

        // Retornar a acomodação recém-criada, mapeada para o objeto de resposta
        return AccommodationMapper.INSTANCE.toAccommodationResponse(accommodation);
    }

    @Transactional(readOnly = true)
    public AccommodationResponse getAccommodationForUser(Long id, String username) {
        // Recuperar a acomodação com base no ID fornecido
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(AccommodationNotFoundException::new);

        // Verificar se a acomodação pertence ao usuário específico
        if (!accommodation.getUser().getUsername().equals(username)) {
            throw new AccommodationOwnershipException();
        }

        // Mapear a acomodação recuperada para o objeto de resposta
        return AccommodationMapper.INSTANCE.toAccommodationResponse(accommodation);
    }

    @Transactional(readOnly = true)
    public List<AccommodationResponse> listAccommodationsForUser(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();
        List<Accommodation> accommodations = accommodationRepository.findByUser(user);
        return AccommodationMapper.INSTANCE.toAccommodationResponseList(accommodations);
    }

    @Transactional(readOnly = true)
    public List<AccommodationResponse> listAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return AccommodationMapper.INSTANCE.toAccommodationResponseList(accommodations);
    }

}
