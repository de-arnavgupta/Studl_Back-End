package de.arnav.studl.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    private Role roleId;

    private String userName;

    private String userEmail;

    private Long organisationRollNo;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String provider;

    private String providerId;

    //Default
    public User() {}

    public User(Long userId, Role roleId, String userName, String userEmail, Long organisationRollNo, Timestamp createdAt, Timestamp updatedAt, String provider, String providerId) {

        this.userId = userId;
        this.roleId = roleId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.organisationRollNo = organisationRollNo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.provider = provider;
        this.providerId = providerId;
    }

    //Getters and Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Role getRoleId() {
        return roleId;
    }
    public void setUserId(Role roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserId(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getOrganisationRollNo() {
        return organisationRollNo;
    }
    public void setOrganisationRollNo(Long organisationRollNo) {
        this.organisationRollNo = organisationRollNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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
}
