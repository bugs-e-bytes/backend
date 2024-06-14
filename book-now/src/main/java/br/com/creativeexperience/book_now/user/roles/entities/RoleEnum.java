package br.com.creativeexperience.book_now.user.roles.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    USER,
    OWNER,
    ADMIN,
    SUPER_ADMIN

}
