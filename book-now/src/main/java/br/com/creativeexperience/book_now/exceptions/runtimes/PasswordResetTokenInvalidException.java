package br.com.creativeexperience.book_now.exceptions.runtimes;

public class PasswordResetTokenInvalidException extends RuntimeException {

    public PasswordResetTokenInvalidException() {
    }

    public PasswordResetTokenInvalidException(String message) {
        super(message);
    }

    public PasswordResetTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordResetTokenInvalidException(Throwable cause) {
        super(cause);
    }
}
