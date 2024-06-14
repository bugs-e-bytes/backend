package br.com.creativeexperience.book_now.exceptions.runtimes;

public class RequiredFieldException extends RuntimeException {

    public RequiredFieldException() {
    }

    public RequiredFieldException(String message) {
        super(message);
    }

    public RequiredFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredFieldException(Throwable cause) {
        super(cause);
    }
}
