package br.com.creativeexperience.book_now.exceptions.runtimes;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException() {
    }

    public BookingNotFoundException(String message) {
        super(message);
    }

    public BookingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingNotFoundException(Throwable cause) {
        super(cause);
    }
}
