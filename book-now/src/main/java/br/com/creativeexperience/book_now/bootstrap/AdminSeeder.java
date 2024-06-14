package br.com.creativeexperience.book_now.bootstrap;

import br.com.creativeexperience.book_now.exceptions.runtimes.RoleNotFoundException;
import br.com.creativeexperience.book_now.user.dto.request.UserRequest;
import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.repositories.UserRepository;
import br.com.creativeexperience.book_now.user.roles.entities.Role;
import br.com.creativeexperience.book_now.user.roles.entities.RoleEnum;
import br.com.creativeexperience.book_now.user.roles.repositories.RoleRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        log.info("Contexto inicializado. Iniciando o carregamento das roles.");
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {

        if (userRepository.existsByRolesName(RoleEnum.SUPER_ADMIN)) {
            log.info("Já existe pelo menos um administrador cadastrado.");
            return;
        }

        String email = "julianemaran@gmail.com";
        String cpf = "060.690.439-52";

        if (userRepository.existsByEmailOrCpf(email, cpf)) {
            log.info("Já existe um usuário cadastrado com o mesmo e-mail ou CPF. Pulando a criação do administrador.");
            return;
        }

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        if (optionalRole.isEmpty()) {
            log.error("Role de SUPER_ADMIN não encontrada. Não é possível criar o administrador.");
            return;
        }

        final var superAdminRole = getSuperAdminRole();

        UserRequest request = UserRequest.builder()
                .name("Juliane Maran")
                .email(email)
                .password(passwordEncoder.encode("P4$$w0rD"))
                .active(true)
                .cpf(cpf)
                .phoneNumber("+5531992199488")
                .address("Avenida Brasil")
                .build();

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .active(request.getActive())
                .cpf(request.getCpf())
                .phoneNumber(request.getPhoneNumber())
                .roles(Set.of(superAdminRole))
                .address(request.getAddress())
                .build();

        userRepository.save(user);
        log.info("Administrador '{}' criado com sucesso!", user.getEmail());

    }

    private Role getSuperAdminRole() {
        return roleRepository.findByName(RoleEnum.SUPER_ADMIN)
                .orElseThrow(() -> new RoleNotFoundException("Permissão SUPER_ADMIN não encontrada."));
    }

}