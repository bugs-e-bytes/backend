package br.com.creativeexperience.book_now.user.auth.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String tokenType;
    private long expiresAt;
    private String username;
    private String email;
    private List<String> roles;

}