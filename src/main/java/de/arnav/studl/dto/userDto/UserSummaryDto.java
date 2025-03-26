package de.arnav.studl.dto.userDto;

import de.arnav.studl.adapter.OrganizationAdapter;
import de.arnav.studl.dto.organizationDto.OrganizationSummaryDto;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class UserSummaryDto {

    private final OrganizationAdapter organizationAdapter;

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull(message = "Organization cannot be null")
    private OrganizationSummaryDto organizationSummaryDto;
    @NotNull(message = "Roles cannot be null")
    private Set<RoleType> roles;

    public UserSummaryDto(OrganizationAdapter organizationAdapter) {
        this.organizationAdapter = organizationAdapter;
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
