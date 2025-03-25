package de.arnav.studl.dto.organizationDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class OrganizationResponseDto {

    @NotNull(message = "Organization Id cannot be null")
    private Long organizationId;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Domain name cannot be blank")
    private String domain;
    @NotNull(message = "Subdomains cannot be null")
    private Set<String> coDomains;
    @NotNull(message = "TLDs cannot be null")
    private Set<String> topLevelDomains;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long id) {
        this.organizationId = id;
    }

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

    public Set<String> getCodomains() {
        return coDomains;
    }

    public void setCodomains(Set<String> coDomains) {
        this.coDomains = coDomains;
    }

    public Set<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public void setTopLevelDomains(Set<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }
}