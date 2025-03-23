package de.arnav.studl.service;

import de.arnav.studl.model.Role;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.List;

public interface RoleService {

    Integer countUsersByRole(RoleType roleType);
    Long findRoleIdByRoleType(RoleType roleType);
    Role findRoleByRoleType(RoleType roleType);
    Role getTopRoleInHierarchy();
    Role getBottomRoleInHierarchy();
    Role findRoleByUserId(Long userId);
    Role findNextHigherRole(RoleType roleType);
    RoleType findRoleTypeById(Long roleId);
    List<User> findUsersByRole(RoleType roleType);
    Boolean userHasRole(Long userId, RoleType roleType);
    Boolean isRoleHigher(RoleType roleType1, RoleType roleType2);

}