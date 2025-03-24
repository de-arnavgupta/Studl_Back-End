package de.arnav.studl.exception;

public class OrganizationNotFoundException extends RuntimeException {
    public OrganizationNotFoundException() {
        super("Organization does not exist.");
    }
}
