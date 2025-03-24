package de.arnav.studl.dto.organizationDto;

import java.util.HashSet;

public class OrganizationCreateDto {

    private String name;
    private String description;
    private String domain;
    private HashSet<String> subDomains;
    private HashSet<String> topLevelDomains;

    public OrganizationCreateDto(String name, String description, String domain, HashSet<String> subDomains, HashSet<String> topLevelDomains) {
        this.name = name;
        this.description = description;
        this.domain = domain;
        this.subDomains = subDomains;
        this.topLevelDomains = topLevelDomains;
    }

    public HashSet<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public void setTopLevelDomains(HashSet<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }

    public HashSet<String> getSubDomains() {
        return subDomains;
    }

    public void setSubDomains(HashSet<String> subDomains) {
        this.subDomains = subDomains;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
