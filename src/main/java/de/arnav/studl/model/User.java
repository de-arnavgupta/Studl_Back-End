package de.arnav.studl.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "organization_sub_domains", joinColumns = @JoinColumn(name = "organization_id"))
    @Enumerated(EnumType.STRING)

    private Set<RoleType> roleType ;

    private String userName;

    private String userEmail;

    @ManyToOne
    private Organization organization;

    private String organisationRollNo;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String provider;

    private String providerId;

    private String password;

    //Default
    public User() {}

    public User(Long userId, Set<RoleType> roleType, String userName, String userEmail, Organization organization, String organisationRollNo, Timestamp createdAt, Timestamp updatedAt, String provider, String providerId, String password) {

        this.userId = userId;
        this.roleType = roleType;
        this.userName = userName;
        this.userEmail = userEmail;
        this.organization = organization;
        this.organisationRollNo = organisationRollNo;
        this.provider = provider;
        this.providerId = providerId;
        this.password = password;
    }

    //Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleType> getRoleType() {
        return roleType;
    }
    public void setRoleType(Set<RoleType> roleType) {
        this.roleType = roleType;
    }

    public String getUserName() {
        return userName;
    }


    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getOrganisationRollNo() {
        return organisationRollNo;
    }
    public void setOrganisationRollNo(String organisationRollNo) {
        this.organisationRollNo = organisationRollNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
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