package de.arnav.studl.model;

import java.util.Set;

public class Organization {

    private Long id;
    private String name;
    private String description;
    private String domain;
    private Set<String> subDomains;
    private Set<String> topLevelDomains;

    public Organization(Long id, String name, String description, String domain, Set<String> subDomains, Set<String> topLevelDomains) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
