package br.com.creativeexperience.book_now.user.auth.controllers;

import br.com.creativeexperience.book_now.security.services.AuthenticationService;
import br.com.creativeexperience.book_now.security.services.JwtService;
import br.com.creativeexperience.book_now.user.auth.dto.LoginRequest;
import br.com.creativeexperience.book_now.user.auth.dto.LoginResponse;
import br.com.creativeexperience.book_now.user.dto.request.UserRequest;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import br.com.creativeexperience.book_now.user.entities.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 36000, allowCredentials = "true",
        value = "http://localhost:4200",
        allowedHeaders = {"Authorization", "Content-Type"},
        methods = {RequestMethod.POST, RequestMethod.GET,
                RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest registerUserDto) {
        UserResponse registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        final var roles = getStrings(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresAt(jwtService.getExpirationTime())
                .username(authenticatedUser.getName())
                .email(authenticatedUser.getEmail())
                .roles(roles)
                .build();

        return ResponseEntity.ok(loginResponse);

    }

    // Convertendo Set<Role> para List<String>
    private static List<String> getStrings(User authenticatedUser) {
        return authenticatedUser.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
    }

}
