package br.com.creativeexperience.book_now.user.password.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String currentPassword;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{8,}$",
            message = "A senha deve ter pelo menos 8 caracteres, incluindo pelo menos uma letra maiúscula, " +
                    "uma letra minúscula, um número e um caractere especial.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

}
