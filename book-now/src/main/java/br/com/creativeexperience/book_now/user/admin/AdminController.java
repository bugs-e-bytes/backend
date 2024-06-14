package br.com.creativeexperience.book_now.user.admin;

import br.com.creativeexperience.book_now.user.dto.request.UserRequest;
import br.com.creativeexperience.book_now.user.dto.response.UserResponse;
import br.com.creativeexperience.book_now.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 36000, allowCredentials = "true",
        value = "http://localhost:4200",
        allowedHeaders = {"Authorization", "Content-Type"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RestController
@RequestMapping("/admins")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/create-admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> createAdministrator(@RequestBody @Valid UserRequest request) {
        UserResponse userResponse = userService.createAdministrator(request);
        log.info("Administrador criado com sucesso: {}", userResponse.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }


}
