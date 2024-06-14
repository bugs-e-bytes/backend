package br.com.creativeexperience.book_now.accommodation.entities;

import br.com.creativeexperience.book_now.user.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accommodations")
public class Accommodation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private BigDecimal price;
    private int maxGuests;
    private String amenities;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

}
