package de.arnav.studl.dto.userRole;

public class UserRoleResponseDto {
    private Long id;
    private String role;
    private Long userId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
