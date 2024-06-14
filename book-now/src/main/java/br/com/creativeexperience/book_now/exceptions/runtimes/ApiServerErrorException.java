package br.com.creativeexperience.book_now.exceptions.runtimes;

public class ApiServerErrorException extends RuntimeException {

    public ApiServerErrorException() {
    }

    public ApiServerErrorException(String message) {
        super(message);
    }

    public ApiServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiServerErrorException(Throwable cause) {
        super(cause);
    }
}
