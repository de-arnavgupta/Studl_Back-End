package de.arnav.studl.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("No user found with the given details");
    }
}
