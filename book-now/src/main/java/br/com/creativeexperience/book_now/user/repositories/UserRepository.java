package br.com.creativeexperience.book_now.user.repositories;

import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.roles.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByRolesName(RoleEnum roleEnum);

    boolean existsByEmailOrCpf(String email, String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
