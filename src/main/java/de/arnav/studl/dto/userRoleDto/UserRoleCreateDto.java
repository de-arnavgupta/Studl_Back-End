package de.arnav.studl.dto.userRoleDto;

public class UserRoleCreateDto {

    private Set<RoleType> roleType;
    private Long userId;

    public Set<RoleType> getRoleType() {
        return roleType;
    }

    public void setRoleType(Set<RoleType> roleType) {
        this.roleType = roleType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
