package br.com.creativeexperience.book_now.exceptions.runtimes;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException() {
    }

    public UserNotRegisteredException(String message) {
        super(message);
    }

    public UserNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotRegisteredException(Throwable cause) {
        super(cause);
    }
}
