package br.com.creativeexperience.book_now.exceptions.runtimes;

/**
 * HTTP STATUS 404 - Not Found
 */
public class PasswordChangeException extends RuntimeException {

    public PasswordChangeException() {
    }

    public PasswordChangeException(String message) {
        super(message);
    }

    public PasswordChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordChangeException(Throwable cause) {
        super(cause);
    }
}
