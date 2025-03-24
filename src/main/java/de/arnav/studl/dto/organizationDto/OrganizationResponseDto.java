package de.arnav.studl.dto.organizationDto;

import java.util.Set;

public class OrganizationResponseDto {
    private Long organizationId;
    private String name;
    private String domain;
    private Set<String> coDomains;
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