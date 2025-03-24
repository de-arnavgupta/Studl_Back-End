package de.arnav.studl.dto.organizationDto;

import java.util.Set;

public class OrganizationCreateDto {

    private String name;
    private String domain;
    private Set<String> subDomains;
    private Set<String> topLevelDomains;

    public Set<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public void setTopLevelDomains(Set<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }

    public Set<String> getSubDomains() {
        return subDomains;
    }

    public void setSubDomains(Set<String> subDomains) {
        this.subDomains = subDomains;
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
