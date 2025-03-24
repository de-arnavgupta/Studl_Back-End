package de.arnav.studl.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException() {
        super();
    }

    public JwtAuthenticationException(Throwable cause) {
        super(cause);
    }
}
