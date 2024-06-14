package br.com.creativeexperience.book_now.user.service;

import br.com.creativeexperience.book_now.exceptions.runtimes.CpfAlreadyExistsException;
import br.com.creativeexperience.book_now.exceptions.runtimes.EmailAlreadyExistsException;
import br.com.creativeexperience.book_now.exceptions.runtimes.RoleNotFoundException;
import br.com.creativeexperience.book_now.exceptions.runtimes.UserNotFoundException;
import br.com.creativeexperience.book_now.user.dto.request.UserRequest;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.mapping.UserMapper;
import br.com.creativeexperience.book_now.user.repositories.UserRepository;
import br.com.creativeexperience.book_now.user.roles.entities.Role;
import br.com.creativeexperience.book_now.user.roles.entities.RoleEnum;
import br.com.creativeexperience.book_now.user.roles.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse createAdministrator(UserRequest input) {

        if (userRepository.existsByEmail(input.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        if (userRepository.existsByCpf(input.getCpf())) {
            throw new CpfAlreadyExistsException("CPF já cadastrado");
        }

        final var adminRole = getAdminRole();

        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .cpf(input.getCpf())
                .phoneNumber(input.getPhoneNumber())
                .address(input.getAddress())
                .active(true)
                .roles(Set.of(adminRole))
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Transactional
    public String updateUser(String email, UserRequest userRequest) {
        final var user = getUser(email);

        if (userRequest.getName() != null) {
            user.setName(userRequest.getName());
        }

        if (userRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(userRequest.getPhoneNumber());
        }

        if (userRequest.getAddress() != null) {
            user.setAddress(userRequest.getAddress());
        }

        userRepository.save(user);
        return "Usuário atualizado com sucesso.";
    }

    @Transactional
    public String deactivateUser(String email) {
        final var user = getUser(email);
        user.setActive(false);
        userRepository.save(user);
        return "Usuário desativado com sucesso.";
    }

    public UserResponse getUserByEmail(String email) {
        final var user = getUser(email);
        return userMapper.toUserResponse(user);
    }

    @Transactional
    public String becomeHost(String email) {

        if (isUserHost(email)) {
            throw new IllegalStateException("Usuário já é um anfitrião");
        }

        final var user = getUser(email);
        final var ownerRole = getOwnerRole();
        user.addRole(ownerRole);
        userRepository.save(user);
        return "Role OWNER adicionada com sucesso!";
    }

    @Transactional(readOnly = true)
    public boolean isUserHost(String email) {
        final var user = getUser(email);

        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName() == RoleEnum.OWNER) {
                return true;
            }
        }
        return false;
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    private Role getOwnerRole() {
        return roleRepository.findByName(RoleEnum.OWNER)
                .orElseThrow(() -> new RoleNotFoundException("Role OWNER não encontrada"));
    }

    private Role getAdminRole() {
        return roleRepository.findByName(RoleEnum.ADMIN)
                .orElseThrow(() -> new RoleNotFoundException("Permissão ADMIN não encontrada."));
    }
}
