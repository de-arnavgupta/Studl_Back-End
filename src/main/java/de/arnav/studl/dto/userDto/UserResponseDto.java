package de.arnav.studl.dto.userDto;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationSummaryDto;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Component
public class UserResponseDto {

    private final OrganizationAdapter organizationAdapter;

    @NotNull(message = "User Id cannot be null")
    private Long userId;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull(message = "Time of creation cannot be null")
    private Timestamp createdAt;
    @NotNull(message = "Time of update cannot be null")
    private Timestamp updatedAt;
    @NotNull(message = "Organization cannot be null")
    private OrganizationSummaryDto organizationSummaryDto;
    @NotNull(message = "Roles cannot be null")
    private Set<RoleType> roles;

    public UserResponseDto(OrganizationAdapter organizationAdapter) {
        this.organizationAdapter = organizationAdapter;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrganizationSummaryDto getOrganization() {
        return organizationSummaryDto;
    }

    public void setOrganizationId(Organization organization) {
        this.organizationSummaryDto = organizationAdapter.toSummaryDto(organization);
    }

    public Set<RoleType> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleType> roles) {
        this.roles = roles;
    }
}
