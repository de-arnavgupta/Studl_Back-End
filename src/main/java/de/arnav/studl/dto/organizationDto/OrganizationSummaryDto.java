package de.arnav.studl.dto.organizationDto;

import jakarta.validation.constraints.NotBlank;

public class OrganizationSummaryDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Domain name cannot be blank")
    private String domain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
