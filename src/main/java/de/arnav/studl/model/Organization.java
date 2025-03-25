package de.arnav.studl.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;

    private String organizationName;

    private String domainName;

    @ElementCollection
    @CollectionTable(name = "organization_tld", joinColumns = @JoinColumn(name = "organization_id"))
    private Set<String> tld = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "organization_sub_domains", joinColumns = @JoinColumn(name = "organization_id"))
    private Set<String> subDomainNames = new HashSet<>();

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Organization() {
    }

    public Organization(String organizationName, String domainName, Set<String> tld, Set<String> subDomainNames, Timestamp createdAt, Timestamp updatedAt) {
        this.organizationName = organizationName;
        this.tld = tld;
        this.domainName = domainName;
        this.subDomainNames = subDomainNames;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDomainName() {
        return domainName;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Set<String> getTld() {
        return tld;
    }
    public void setTld(Set<String> tld) {
        this.tld = tld;
    }

    public Set<String> getSubDomainNames() {
        return subDomainNames;
    }
    public void setSubDomainNames(Set<String> subDomainNames) {
        this.subDomainNames = subDomainNames;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
