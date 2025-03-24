package de.arnav.studl.service.implementation;

import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userRoleDto.UserRoleCreateDto;
import de.arnav.studl.dto.userRoleDto.UserRoleResponseDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Override
    public Integer countUsersByRole(RoleType roleType) {
        return 0;
    }

    @Override
    public RoleType getTopRoleInHierarchy() {
        return null;
    }

    @Override
    public RoleType getBottomRoleInHierarchy() {
        return null;
    }

    @Override
    public RoleType findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public RoleType findNextHigherRole(RoleType roleType) {
        return null;
    }

    @Override
    public RoleType findRoleTypeById(Long roleId) {
        return null;
    }

    @Override
    public List<UserResponseDto> findUsersByRole(RoleType roleType) {
        return List.of();
    }

    @Override
    public Boolean hasRole(Long userId, RoleType roleType) {
        return null;
    }

    @Override
    public Boolean isRoleHigher(RoleType roleType1, RoleType roleType2) {
        return null;
    }

    @Override
    public UserRoleResponseDto assignRole(UserRoleCreateDto dto) {
        return null;
    }

    @Override
    public List<UserRoleResponseDto> getUserRolesByUser(Long userId) {
        return List.of();
    }

    @Override
    public void removeRoleFromUser(Long userRoleId) {

    }
}
