package br.com.creativeexperience.book_now.accommodation.repositories;

import br.com.creativeexperience.book_now.accommodation.entities.Accommodation;
import br.com.creativeexperience.book_now.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {

    List<Accommodation> findByUser(User user);

}
