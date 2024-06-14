package br.com.creativeexperience.book_now.accommodation.entities;

import br.com.creativeexperience.book_now.booking.entities.Booking;
import br.com.creativeexperience.book_now.user.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

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


    @Size(min = 10, max = 20, message = "O endereço deve ter entre {min} e {max} caracteres.")
    private String title;

    @Size(min = 10, max = 250, message = "O endereço deve ter entre {min} e {max} caracteres.")
    private String location;

    @PositiveOrZero
    private BigDecimal price;

    @Min(value = 1, message = "Número de hóspedes deve ser no mínimo {value}")
    @Max(value = 50, message = "Número de hóspedes não pode exceder o limite de {value}")
    private int maxGuests;

    @Size(min = 10, max = 200, message = "O endereço deve ter entre {min} e {max} caracteres.")
    private String amenities;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime updatedAt;

}
