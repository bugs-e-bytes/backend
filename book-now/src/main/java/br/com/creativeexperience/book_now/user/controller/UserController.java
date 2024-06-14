package br.com.creativeexperience.book_now.user.controller;

import br.com.creativeexperience.book_now.security.services.JwtService;
import br.com.creativeexperience.book_now.user.dto.request.UserRequest;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import br.com.creativeexperience.book_now.user.entities.User;
import br.com.creativeexperience.book_now.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 36000, allowCredentials = "true",
        value = "http://localhost:4200",
        allowedHeaders = {"Authorization", "Content-Type"},
        methods = {RequestMethod.POST, RequestMethod.GET,
                RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        UserResponse userResponse = userService.getUserByEmail(currentUser.getEmail());
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String message = userService.updateUser(currentPrincipalName, request);
        log.info("Usuário atualizado com sucesso: {}", currentPrincipalName);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deactivateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String message = userService.deactivateUser(currentPrincipalName);
        log.info("Usuário desativado com sucesso: {}", currentPrincipalName);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/become-host")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> becomeHost(@RequestHeader("Authorization") String token) {
        final var userEmail = authenticatedUser(token);
        if (userService.isUserHost(userEmail)) {
            return ResponseEntity.badRequest().body("Você já é um anfitrião!");
        }
        userService.becomeHost(userEmail);
        return ResponseEntity.ok().body("Role OWNER adicionada com sucesso!");
    }

    @GetMapping("/is-host")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> isUserHost(@RequestHeader("Authorization") String token) {
        final var userEmail = authenticatedUser(token);
        boolean isHost = userService.isUserHost(userEmail);
        return ResponseEntity.ok(isHost);
    }

    private String authenticatedUser(String token) {
        String jwt = token.substring(7);
        return jwtService.extractUsername(jwt);
    }

}
