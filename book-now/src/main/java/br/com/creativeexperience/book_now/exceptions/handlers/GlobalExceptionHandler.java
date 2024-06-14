package br.com.creativeexperience.book_now.exceptions.handlers;

import br.com.creativeexperience.book_now.exceptions.dto.ExceptionEnum;
import br.com.creativeexperience.book_now.exceptions.dto.ExceptionResponse;
import br.com.creativeexperience.book_now.exceptions.runtimes.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AccommodationNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleAccommodationNotFoundException(AccommodationNotFoundException ex) {
        log.info("Acomodação não encontrada.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.ACCOMMODATION_NOT_FOUND);
    }

    @ExceptionHandler({AccommodationOwnershipException.class})
    public ResponseEntity<ExceptionResponse> handleAccommodationOwnershipException(AccommodationOwnershipException ex) {
        log.info("Acomodação não pertence ao usuário.");
        return buildResponseEntity(ex, HttpStatus.FORBIDDEN, ExceptionEnum.ACCOMMODATION_OWNERSHIP);
    }

    @ExceptionHandler({
            Exception.class,
            ApiServerErrorException.class,
            UpdateFailureException.class,
            InternalAuthenticationServiceException.class
    })
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.info("Erro interno no servidor.");
        return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR, ExceptionEnum.SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        log.info("Requisição inválida.");
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST, ExceptionEnum.REQUIRED_FIELD);
    }

    @ExceptionHandler({CpfAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> handleCpfAlreadyExistsException(CpfAlreadyExistsException ex) {
        log.info("CPF já em uso.");
        return buildResponseEntity(ex, HttpStatus.CONFLICT, ExceptionEnum.CPF_ALREADY_EXISTS);
    }

    @ExceptionHandler({CpfNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleCpfNotFoundException(CpfNotFoundException ex) {
        log.info("CPF não encontrado.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.CPF_NOT_FOUND);
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.info("Email já em uso.");
        return buildResponseEntity(ex, HttpStatus.CONFLICT, ExceptionEnum.EMAIL_ALREADY_EXISTS);
    }

    @ExceptionHandler({EmailNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleEmailNotFoundException(EmailNotFoundException ex) {
        log.info("Email não encontrado.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.EMAIL_NOT_FOUND);
    }

    @ExceptionHandler({EmailSendException.class})
    public ResponseEntity<ExceptionResponse> handleEmailSendException(EmailSendException ex) {
        log.info("Email não enviado.");
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST, ExceptionEnum.EMAIL_SEND);
    }

    @ExceptionHandler({IncorrectEmailException.class})
    public ResponseEntity<ExceptionResponse> handleIncorrectEmailException(IncorrectEmailException ex) {
        log.info("Email incorreto.");
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST, ExceptionEnum.EMAIL_INCORRECT);
    }

    @ExceptionHandler({IncorrectPasswordException.class})
    public ResponseEntity<ExceptionResponse> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        log.info("Senha incorreta.");
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST, ExceptionEnum.PASSWORD_INCORRECT);
    }

    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidTokenException(InvalidTokenException ex) {
        log.info("Senha incorreta.");
        return buildResponseEntity(ex, HttpStatus.FORBIDDEN, ExceptionEnum.INVALID_TOKEN);
    }

    @ExceptionHandler({PasswordChangeException.class})
    public ResponseEntity<ExceptionResponse> handlePasswordChangeException(PasswordChangeException ex) {
        log.info("Erro ao alterar a senha.");
        return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR, ExceptionEnum.PASSWORD_CHANGE);
    }

    @ExceptionHandler({PasswordDoNotMatchException.class})
    public ResponseEntity<ExceptionResponse> handlePasswordDoNotMatchException(PasswordDoNotMatchException ex) {
        log.info("As senhas não coincidem.");
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST, ExceptionEnum.PASSWORD_NOT_MATCH);
    }

    @ExceptionHandler({RequiredFieldException.class})
    public ResponseEntity<ExceptionResponse> handleRequiredFieldException(RequiredFieldException ex) {
        log.info("Campo obrigatório.");
        return buildResponseEntity(ex, HttpStatus.BAD_REQUEST, ExceptionEnum.REQUIRED_FIELD);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info("Recurso não encontrado.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler({RoleNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        log.info("Permissão USER não encontrada.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.ROLE_NOT_FOUND);
    }

    @ExceptionHandler({UserInactiveException.class})
    public ResponseEntity<ExceptionResponse> handleUserInactiveException(UserInactiveException ex) {
        log.info("Usuário inativo.");
        return buildResponseEntity(ex, HttpStatus.FORBIDDEN, ExceptionEnum.USER_INACTIVE);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        log.info("Usuário não encontrado.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.USER_NOT_FOUND);
    }

    @ExceptionHandler({UserNotRegisteredException.class})
    public ResponseEntity<ExceptionResponse> handleUserNotRegisteredException(UserNotRegisteredException ex) {
        log.info("Usuário não cadastrado.");
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND, ExceptionEnum.USER_NOT_REGISTERED);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(Exception ex,
                                                                  HttpStatus status,
                                                                  ExceptionEnum exceptionEnum) {
        log.error("Erro: {} - {}", status, ex.getMessage(), ex);
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exceptionEnum.getMessage())
                .build();
        return ResponseEntity.status(status).body(response);
    }

}
