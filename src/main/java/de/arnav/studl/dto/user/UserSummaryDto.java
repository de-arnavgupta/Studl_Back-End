package de.arnav.studl.dto.user;

public class UserSummaryDto {
    private Long userId;
    private String name;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
