package br.com.creativeexperience.book_now.exceptions.runtimes;

public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException() {
    }

    public AccommodationNotFoundException(String message) {
        super(message);
    }

    public AccommodationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccommodationNotFoundException(Throwable cause) {
        super(cause);
    }
}
