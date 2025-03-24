package de.arnav.studl.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long userId;
    private String name;
    private String email;
    private String password;
    private Organization organization;
    private Set<RoleType> roles;

    public User(Long userId, String name, String email, String password, Organization organization, Set<RoleType> roles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.organization = organization;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<RoleType> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleType> roles) {
        this.roles = roles;
    }
}
