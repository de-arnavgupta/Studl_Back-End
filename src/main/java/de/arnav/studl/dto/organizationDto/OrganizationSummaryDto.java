package de.arnav.studl.dto.organizationDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class OrganizationSummaryDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Domain name cannot be blank")
    private String domain;
    @NotNull(message = "Top Level Domains (TLDs) cannot be blank")
    private Set<String> topLevelDomains;

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

    public Set<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public void setTopLevelDomains(Set<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }
}
