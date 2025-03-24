package de.arnav.studl.exception;

public class LogoutFailedException extends RuntimeException {
    public LogoutFailedException() {
        super("Logout attempt failed. Token missing or invalid.");
    }
}
