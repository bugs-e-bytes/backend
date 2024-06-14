package br.com.creativeexperience.book_now.exceptions.runtimes;

public class AccommodationOwnershipException extends RuntimeException {

    public AccommodationOwnershipException() {
    }

    public AccommodationOwnershipException(String message) {
        super(message);
    }

    public AccommodationOwnershipException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccommodationOwnershipException(Throwable cause) {
        super(cause);
    }
}
