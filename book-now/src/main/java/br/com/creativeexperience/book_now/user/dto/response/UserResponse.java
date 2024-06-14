package br.com.creativeexperience.book_now.user.dto.response;

import br.com.creativeexperience.book_now.user.roles.dto.RoleResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.OffsetDateTime;

/**
 * Representa os dados que serão retornados em uma resposta ao consultar ou manipular informações de usuário.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phoneNumber;
    private String address;
    private Boolean active;
    private RoleResponse role;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime updatedAt;
}