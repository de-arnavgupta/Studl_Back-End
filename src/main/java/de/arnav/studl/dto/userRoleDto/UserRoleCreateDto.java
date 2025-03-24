package de.arnav.studl.dto.userRoleDto;

import de.arnav.studl.model.RoleType;

public class UserRoleCreateDto {

    private RoleType roleType;
    private Long userId;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
