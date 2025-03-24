package de.arnav.studl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    // Security & Authentication Exceptions
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleJwtAuthenticationException(JwtAuthenticationException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Authentication Failed", ex.getMessage());
    }

    @ExceptionHandler(LogoutFailedException.class)
    public ResponseEntity<Map<String, Object>> handleLogoutFailedException(LogoutFailedException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Logout Failed", ex.getMessage());
    }

    //User Management Exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());
    }

    @ExceptionHandler(RoleAssignmentException.class)
    public ResponseEntity<Map<String, Object>> handleRoleAssignmentException(RoleAssignmentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Role Assignment Error", ex.getMessage());
    }

    // Organization Exceptions
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrganizationNotFoundException(OrganizationNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Organization Not Found", ex.getMessage());
    }

    @ExceptionHandler(DuplicateOrganizationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateOrganizationException(DuplicateOrganizationException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Duplicate Organization", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", "Invalid argument provided.");
    }


    // Helper Method to Build Responses
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", error,
                "message", message
        ));
    }
}
