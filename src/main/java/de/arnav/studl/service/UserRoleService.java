package de.arnav.studl.service;

import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.List;

public interface UserRoleService {

    Integer countUsersByRole(RoleType roleType);
    RoleType getTopRoleInHierarchy();
    RoleType getBottomRoleInHierarchy();
    RoleType findRoleByUserId(Long userId);
    RoleType findNextHigherRole(RoleType roleType);
    RoleType findRoleTypeById(Long roleId);
    List<User> findUsersByRole(RoleType roleType);
    Boolean hasRole(Long userId, RoleType roleType);
    Boolean isRoleHigher(RoleType roleType1, RoleType roleType2);

}