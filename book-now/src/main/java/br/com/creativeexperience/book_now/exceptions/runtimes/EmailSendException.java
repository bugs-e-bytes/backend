package br.com.creativeexperience.book_now.exceptions.runtimes;

/**
 * HTTP STATUS 404 - Not Found
 */
public class EmailSendException extends RuntimeException {

    public EmailSendException() {
    }

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendException(Throwable cause) {
        super(cause);
    }
}
