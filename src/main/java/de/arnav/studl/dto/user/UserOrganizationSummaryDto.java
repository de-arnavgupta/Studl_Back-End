package de.arnav.studl.dto.user;

public class UserOrganizationSummaryDto {
    private Long organizationId;
    private String name;

    public Long getOrganizationId() { return organizationId; }
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
