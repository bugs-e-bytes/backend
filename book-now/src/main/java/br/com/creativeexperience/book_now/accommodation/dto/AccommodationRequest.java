package br.com.creativeexperience.book_now.accommodation.dto;

import lombok.*;

import java.math.BigDecimal;

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
