package de.arnav.studl.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;

    private String name;
    private String domain;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "organization_codomains", joinColumns = @JoinColumn(name = "organization_id"))
    @Column(name = "codomain")
    private List<String> codomains = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "organization_top_level_domains", joinColumns = @JoinColumn(name = "organization_id"))
    @Column(name = "top_level_domain")
    private List<String> topLevelDomains = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<User> users;

    public Organization() {}

    public Long getOrganizationId() { return organizationId; }
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public List<String> getCodomains() { return codomains; }
    public void setCodomains(List<String> codomains) { this.codomains = codomains; }
    public List<String> getTopLevelDomains() { return topLevelDomains; }
    public void setTopLevelDomains(List<String> topLevelDomains) { this.topLevelDomains = topLevelDomains; }
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}
