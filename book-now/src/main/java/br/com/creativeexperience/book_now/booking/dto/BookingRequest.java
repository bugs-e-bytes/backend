package br.com.creativeexperience.book_now.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDate;

/**
 * Representa os dados que podem ser recebidos em uma requisição para criar ou atualizar uma reserva.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private Long accommodationId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "A data de início deve ser no futuro")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "A data fim deve ser no futuro")
    private LocalDate endDate;

    private int numGuests;

}
