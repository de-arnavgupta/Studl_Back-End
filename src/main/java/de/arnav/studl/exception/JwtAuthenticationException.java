package de.arnav.studl.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException() {
        super("Invalid or expired JWT token");
    }

}
