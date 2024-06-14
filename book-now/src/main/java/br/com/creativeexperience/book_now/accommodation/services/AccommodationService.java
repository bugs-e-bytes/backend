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
        User user = userRepository.findByEmail(username).orElseThrow();
        Accommodation accommodation = AccommodationMapper.INSTANCE.toAccommodation(request);
        accommodation.setUser(user);
        accommodation = accommodationRepository.save(accommodation);
        return AccommodationMapper.INSTANCE.toAccommodationResponse(accommodation);
    }

    @Transactional(readOnly = true)
    public AccommodationResponse getAccommodationForUser(Long id, String username) {
        final var accommodation = getAccommodation(id);
        if (!accommodation.getUser().getUsername().equals(username)) {
            throw new AccommodationOwnershipException();
        }
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

    @Transactional
    public void deleteAccommodationForUser(Long id, String username) {
        final var accommodation = getAccommodation(id);
        if (!accommodation.getUser().getUsername().equals(username)) {
            throw new AccommodationOwnershipException();
        }
        accommodationRepository.delete(accommodation);
    }

    @Transactional
    public AccommodationResponse updateAccommodationForUser(Long id, AccommodationRequest request, String username) {
        var accommodation = getAccommodation(id);
        if (!accommodation.getUser().getUsername().equals(username)) {
            throw new AccommodationOwnershipException();
        }
        accommodation.setTitle(request.getTitle());
        accommodation.setLocation(request.getLocation());
        accommodation.setPrice(request.getPrice());
        accommodation.setMaxGuests(request.getMaxGuests());
        accommodation.setAmenities(request.getAmenities());
        accommodation = accommodationRepository.save(accommodation);
        return AccommodationMapper.INSTANCE.toAccommodationResponse(accommodation);
    }

    private Accommodation getAccommodation(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(AccommodationNotFoundException::new);
    }

    public void getAccommodationById(Long id) {
        accommodationRepository.findById(id);
    }
}
