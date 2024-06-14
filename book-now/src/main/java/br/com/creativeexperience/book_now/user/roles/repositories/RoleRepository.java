package br.com.creativeexperience.book_now.user.roles.repositories;

import br.com.creativeexperience.book_now.user.roles.entities.Role;
import br.com.creativeexperience.book_now.user.roles.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);

}
