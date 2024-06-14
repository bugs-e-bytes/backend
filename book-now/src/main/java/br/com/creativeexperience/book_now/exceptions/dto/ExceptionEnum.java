package br.com.creativeexperience.book_now.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    REQUIRED_FIELD("O campo é obrigatório."), // 400 BAD REQUEST
    PASSWORD_NOT_MATCH("As senhas não coincidem."), // 400 BAD REQUEST
    EMAIL_INCORRECT("Email incorreto."), // 400 BAD REQUEST
    PASSWORD_INCORRECT("Senha incorreta"), // 400 BAD REQUEST
    UNAUTHORIZED_USER("Usuário não possui permissão para acessar."), // 401 UNAUTHORIZED
    USER_INACTIVE("Usuário está inativo e não pode realizar login."), // 403 FORBIDDEN
    ACCOMMODATION_OWNERSHIP("Acomodação não pertence ao usuário."), // 403 FORBIDDEN
    CPF_NOT_FOUND("CPF não encontrado."), // 404 NOT FOUND
    EMAIL_NOT_FOUND("E-mail não encontrado."), // 404 NOT FOUND
    USER_NOT_FOUND("Usuário não encontrado."), // 404 NOT FOUND
    ROLE_NOT_FOUND("Role não encontrada."), // 404 NOT FOUND
    BOOKING_NOT_FOUND("Reserva não encontrada."), // 404 NOT FOUND
    RESOURCE_NOT_FOUND("Recurso não encontrada."), // 404 NOT FOUND
    USER_NOT_REGISTERED("Usuário não cadastrado."), // 404 NOT FOUND
    ACCOMMODATION_NOT_FOUND("Acomodação não cadastrado."), // 404 NOT FOUND
    CPF_ALREADY_EXISTS("CPF já está em uso."), // 409 CONFLICT
    EMAIL_ALREADY_EXISTS("Email já está em uso."), // 409 CONFLICT
    EMAIL_SEND("Email não pode ser enviado."),
    INVALID_TOKEN("Token inválido."),
    PASSWORD_CHANGE("Erro ao alterar a senha."), // 500 INTERNAL
    UPDATE_FAILURE("Falha na atualização."),
    SERVER_ERROR("Ops... Ocorreu um erro interno no servidor. Tente novamente mais tarde."),
    SERVICE_UNAVAILABLE("Desculpe, estamos em manutenção.");

    private final String message;

}
