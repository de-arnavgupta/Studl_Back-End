package de.arnav.studl.service.implementation;

import de.arnav.studl.model.Role;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public Integer countUsersByRole(RoleType roleType) {
        return 0;
    }

    @Override
    public Long findRoleIdByRoleType(RoleType roleType) {
        return 0L;
    }

    @Override
    public Role findRoleByRoleType(RoleType roleType) {
        return null;
    }

    @Override
    public Role getTopRoleInHierarchy() {
        return null;
    }

    @Override
    public Role getBottomRoleInHierarchy() {
        return null;
    }

    @Override
    public Role findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role findNextHigherRole(RoleType roleType) {
        return null;
    }

    @Override
    public RoleType findRoleTypeById(Long roleId) {
        return null;
    }

    @Override
    public List<User> findUsersByRole(RoleType roleType) {
        return List.of();
    }

    @Override
    public Boolean userHasRole(Long userId, RoleType roleType) {
        return null;
    }

    @Override
    public Boolean isRoleHigher(RoleType roleType1, RoleType roleType2) {
        return null;
    }
}
