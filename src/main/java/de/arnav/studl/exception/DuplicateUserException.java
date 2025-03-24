package de.arnav.studl.exception;

/**
 * Exception thrown when a user with the same email or username already exists.
 */
public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {

        super("User with this email or username already exists.");
    }
}
