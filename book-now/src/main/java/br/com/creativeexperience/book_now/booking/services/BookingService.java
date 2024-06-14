package br.com.creativeexperience.book_now.booking.services;

import br.com.creativeexperience.book_now.accommodation.dto.AccommodationResponse;
import br.com.creativeexperience.book_now.accommodation.entities.Accommodation;
import br.com.creativeexperience.book_now.accommodation.repositories.AccommodationRepository;
import br.com.creativeexperience.book_now.booking.dto.BookingRequest;
import br.com.creativeexperience.book_now.booking.dto.BookingResponse;
import br.com.creativeexperience.book_now.booking.entities.Booking;
import br.com.creativeexperience.book_now.booking.mapping.BookingMapper;
import br.com.creativeexperience.book_now.booking.repositories.BookingRepository;
import br.com.creativeexperience.book_now.exceptions.runtimes.AccommodationNotFoundException;
import br.com.creativeexperience.book_now.exceptions.runtimes.UserNotFoundException;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class BookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final AccommodationRepository accommodationRepository;

    @Transactional
    public BookingResponse createBooking(BookingRequest request, String username) {

        if (request.getAccommodationId() == null) {
            throw new IllegalArgumentException("O ID da acomodação não pode ser nulo");
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);

        Accommodation accommodation = accommodationRepository.findById(request.getAccommodationId())
                .orElseThrow(AccommodationNotFoundException::new);

        var booking = new Booking();
        booking.setAccommodation(accommodation);
        booking.setUser(user);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setNumGuests(request.getNumGuests());

        booking = bookingRepository.save(booking);

        // Retornar resposta
        var response = new BookingResponse();
        response.setId(booking.getId());
        response.setAccommodation(mapToAccommodationResponse(booking.getAccommodation()));
        response.setUser(mapToUserResponse(booking.getUser()));
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setNumGuests(booking.getNumGuests());
        response.setCreatedAt(booking.getCreatedAt());
        response.setUpdatedAt(booking.getUpdatedAt());

        return response;
    }

    private AccommodationResponse mapToAccommodationResponse(Accommodation accommodation) {
        var response = new AccommodationResponse();
        response.setTitle(accommodation.getTitle());
        response.setLocation(accommodation.getLocation());
        response.setPrice(accommodation.getPrice());
        response.setMaxGuests(accommodation.getMaxGuests());
        response.setAmenities(accommodation.getAmenities());
        return response;
    }

    private UserResponse mapToUserResponse(User user) {
        var response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCpf(user.getCpf());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAddress(user.getAddress());
        return response;
    }

}
