package de.arnav.studl.exception;

public class OrganizationNotFoundException extends RuntimeException {
    public OrganizationNotFoundException() {
        super("Cannot delete: Organization does not exist.");
    }
}
