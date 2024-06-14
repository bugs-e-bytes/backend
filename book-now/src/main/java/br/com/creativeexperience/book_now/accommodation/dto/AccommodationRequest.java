package br.com.creativeexperience.book_now.accommodation.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Representa os dados que podem ser recebidos em uma requisição para criar ou atualizar uma acomodação.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationRequest {

    private String title;
    private String location;
    private BigDecimal price;
    private int maxGuests;
    private String amenities;
    private String imageUrl;

}
