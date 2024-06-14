package br.com.creativeexperience.book_now.user.roles.dto;

import br.com.creativeexperience.book_now.user.roles.entities.RoleEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

    private RoleEnum name;
    private String description;

}