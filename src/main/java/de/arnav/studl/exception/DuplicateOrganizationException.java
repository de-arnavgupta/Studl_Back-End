package de.arnav.studl.exception;

public class DuplicateOrganizationException extends RuntimeException {
    public DuplicateOrganizationException() {
        super("Organization with this name already exists.");
    }
}
