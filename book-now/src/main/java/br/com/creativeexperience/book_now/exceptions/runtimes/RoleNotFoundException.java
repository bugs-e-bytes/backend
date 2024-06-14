package br.com.creativeexperience.book_now.exceptions.runtimes;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }
}
