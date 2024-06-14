package br.com.creativeexperience.book_now.booking.repositories;

import br.com.creativeexperience.book_now.booking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
