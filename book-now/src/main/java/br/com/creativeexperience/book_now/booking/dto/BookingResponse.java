package br.com.creativeexperience.book_now.booking.dto;

import br.com.creativeexperience.book_now.accommodation.dto.AccommodationResponse;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * Representa os dados que serão retornados em uma resposta ao consultar ou manipular informações de reserva.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Long id;
    private AccommodationResponse accommodation;
    private UserResponse user;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numGuests;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime updatedAt;

}
