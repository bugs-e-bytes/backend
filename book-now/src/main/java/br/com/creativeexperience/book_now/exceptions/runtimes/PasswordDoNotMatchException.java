package br.com.creativeexperience.book_now.exceptions.runtimes;

public class PasswordDoNotMatchException extends RuntimeException {

    public PasswordDoNotMatchException() {
    }

    public PasswordDoNotMatchException(String message) {
        super(message);
    }

    public PasswordDoNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordDoNotMatchException(Throwable cause) {
        super(cause);
    }
}
