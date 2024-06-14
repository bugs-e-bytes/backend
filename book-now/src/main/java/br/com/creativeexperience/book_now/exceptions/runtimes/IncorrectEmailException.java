package br.com.creativeexperience.book_now.exceptions.runtimes;

public class IncorrectEmailException extends RuntimeException {

    public IncorrectEmailException() {
    }

    public IncorrectEmailException(String message) {
        super(message);
    }

    public IncorrectEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectEmailException(Throwable cause) {
        super(cause);
    }
}
