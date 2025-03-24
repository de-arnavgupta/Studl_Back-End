package de.arnav.studl.dto.organizationDto;

import java.util.HashSet;

public class OrganizationResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final String domain;
    private final HashSet<String> subDomains;
    private final HashSet<String> topLevelDomains;

    public OrganizationResponseDto(Long id, String name, String description, String domain, HashSet<String> subDomains, HashSet<String> topLevelDomains) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.domain = domain;
        this.subDomains = subDomains;
        this.topLevelDomains = topLevelDomains;
    }

    public Long getId() {
        return id;
    }

    public HashSet<String> getTopLevelDomains() {
        return topLevelDomains;
    }

    public HashSet<String> getSubDomains() {
        return subDomains;
    }

    public String getDomain() {
        return domain;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}