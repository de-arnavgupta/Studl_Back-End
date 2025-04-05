package de.arnav.studl.dto.organization;

import java.util.List;

public class OrganizationResponseDto {
    private Long organizationId;
    private String name;
    private String domain;
    private List<String> codomains;
    private List<String> topLevelDomains;

    public Long getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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
    public List<String> getCodomains() {
        return codomains;
    }
    public void setCodomains(List<String> codomains) {
        this.codomains = codomains;
    }
    public List<String> getTopLevelDomains() {
        return topLevelDomains;
    }
    public void setTopLevelDomains(List<String> topLevelDomains) {
        this.topLevelDomains = topLevelDomains;
    }
}
