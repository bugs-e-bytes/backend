package br.com.creativeexperience.book_now.user.password.dto;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetEmailRequest {

    @Email(message = "Por favor, insira um endereço de e-mail válido.",
            regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

}
