package br.com.creativeexperience.book_now.user.password.controllers;

import br.com.creativeexperience.book_now.user.password.dto.NewPasswordRequest;
import br.com.creativeexperience.book_now.user.password.dto.PasswordResetEmailRequest;
import br.com.creativeexperience.book_now.user.password.dto.UpdatePasswordRequest;
import br.com.creativeexperience.book_now.user.password.services.PasswordService;
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
@RequestMapping("/password")
@AllArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PutMapping("/update-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> updatePassword(@RequestBody @Valid UpdatePasswordRequest passwordUpdateRequest) {
        log.info("Iniciando atualização de senha para o usuário.");
        passwordService.updatePassword(passwordUpdateRequest);
        log.info("Senha atualizada com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso!");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid PasswordResetEmailRequest request) {
        log.info("Recebida solicitação para redefinir senha do usuário com email: {}", request.getEmail());
        passwordService.resetPassword(request.getEmail());
        log.info("Link de redefinição de senha enviado com sucesso para o email: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body("O link de redefinição de senha foi enviado para seu e-mail.");
    }

    @PostMapping("/reset-password/confirm")
    public ResponseEntity<String> confirmResetPassword(@RequestParam(name = "token") String token,
                                                       @RequestBody @Valid NewPasswordRequest resetPasswordRequest) {
        log.info("Recebida solicitação para confirmar reset de senha com token: {}", token);
        if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())) {
            log.error("As senhas não conferem.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As senhas não conferem.");
        }
        passwordService.updatePasswordWithToken(token, resetPasswordRequest.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK).body("Reset de senha realizado com sucesso!");
    }

}
