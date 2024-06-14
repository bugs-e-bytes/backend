package br.com.creativeexperience.book_now.exceptions.runtimes;

/**
 * HTTP STATUS 409 - Conflict
 */
public class UserInactiveException extends RuntimeException {

    public UserInactiveException() {
    }

    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInactiveException(Throwable cause) {
        super(cause);
    }
}
