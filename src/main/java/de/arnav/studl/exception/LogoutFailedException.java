package de.arnav.studl.exception;

public class LogoutFailedException extends RuntimeException {
    public LogoutFailedException(String message) {
        super(message);
    }
}
