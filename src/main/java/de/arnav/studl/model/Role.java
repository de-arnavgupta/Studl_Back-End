package de.arnav.studl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String description;

    public Role(){}

    public Role(Long roleId, RoleType roleType, String description){
        this.roleId = roleId;
        this.roleType = roleType;
        this.description = description;
    }

    public Long getRoleId(){
        return roleId;
    }

    public RoleType getRoleType(){
        return roleType;
    }
    public void setRoleId(RoleType roleType){
        this.roleType = roleType;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
