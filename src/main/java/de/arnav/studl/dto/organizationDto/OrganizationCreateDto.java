package de.arnav.studl.dto.organizationDto;

import java.util.Set;

public class OrganizationCreateDto {

    private String name;
    private String domain;
    private Set<String> codomains;
    private Set<String> topLevelDomains;

    public Set<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public void setTopLevelDomains(Set<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }

    public Set<String> getCodomains() {
        return codomains;
    }

    public void setCodomains(Set<String> codomains) {
        this.codomains = codomains;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
