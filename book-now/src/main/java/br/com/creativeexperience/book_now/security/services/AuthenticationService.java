package br.com.creativeexperience.book_now.security.services;

import br.com.creativeexperience.book_now.exceptions.runtimes.CpfAlreadyExistsException;
import br.com.creativeexperience.book_now.exceptions.runtimes.EmailAlreadyExistsException;
import br.com.creativeexperience.book_now.exceptions.runtimes.RoleNotFoundException;
import br.com.creativeexperience.book_now.exceptions.runtimes.UserNotFoundException;
import br.com.creativeexperience.book_now.user.auth.dto.LoginRequest;
import br.com.creativeexperience.book_now.user.dto.request.UserRequest;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import br.com.creativeexperience.book_now.user.roles.entities.Role;
import br.com.creativeexperience.book_now.user.roles.entities.RoleEnum;
import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.mapping.UserMapper;
import br.com.creativeexperience.book_now.user.roles.repositories.RoleRepository;
import br.com.creativeexperience.book_now.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserResponse signup(UserRequest input) {

        checkEmail(input);
        checkCpf(input);

        final var userRole = getUserRole();

        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .cpf(input.getCpf())
                .phoneNumber(input.getPhoneNumber())
                .address(input.getAddress())
                .active(true)
                .roles(Set.of(userRole))
                .build();

        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.toUserResponse(savedUser);
    }

    private Role getUserRole() {
        return roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RoleNotFoundException("Role USER não encontrada"));
    }

    private void checkCpf(UserRequest input) {
        if (userRepository.existsByCpf(input.getCpf())) {
            throw new CpfAlreadyExistsException("CPF já cadastrado");
        }
    }

    private void checkEmail(UserRequest input) {
        if (userRepository.existsByEmail(input.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }
    }

    @Transactional(readOnly = true)
    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

}
