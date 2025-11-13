package hr.java.vjezbe.exceptions;

public class TimeoutException extends RuntimeException {
    public TimeoutException(String message) {
        super(message);
    }
}
