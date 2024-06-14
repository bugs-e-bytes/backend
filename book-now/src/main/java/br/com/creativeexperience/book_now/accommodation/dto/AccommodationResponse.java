package br.com.creativeexperience.book_now.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Representa os dados que serão retornados em uma resposta ao consultar ou manipular informações de acomodação.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationResponse {

    private Long id;
    private String title;
    private String location;
    private BigDecimal price;
    private int maxGuests;
    private String amenities;
    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime updatedAt;

}
