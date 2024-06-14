package br.com.creativeexperience.book_now.exceptions.runtimes;

public class UpdateFailureException extends RuntimeException {

    public UpdateFailureException() {
    }

    public UpdateFailureException(String message) {
        super(message);
    }

    public UpdateFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateFailureException(Throwable cause) {
        super(cause);
    }
}
