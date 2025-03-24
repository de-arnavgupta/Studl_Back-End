package de.arnav.studl.exception;

public class LogoutFailedException extends RuntimeException {
    public LogoutFailedException() {
        super();
    }

    public LogoutFailedException(Throwable cause) {
        super(cause);
    }
}
