package de.arnav.studl.dto.organizationDto;

import java.util.Set;

public class OrganizationResponseDto {
    private Long id;
    private String name;
    private String domain;
    private Set<String> subDomains;
    private Set<String> topLevelDomains;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<String> getSubDomains() {
        return subDomains;
    }

    public void setSubDomains(Set<String> subDomains) {
        this.subDomains = subDomains;
    }

    public Set<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public void setTopLevelDomains(Set<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }
}