package de.arnav.studl.service;

import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userRoleDto.UserRoleCreateDto;
import de.arnav.studl.dto.userRoleDto.UserRoleResponseDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.Set;
import java.util.List;

public interface UserRoleService {

    Integer countUsersByRole(RoleType roleType);
    Set<RoleType> findRolesByUserId(Long userId);
    RoleType findNextRole(RoleType roleType);
    RoleType findPrevRole(RoleType roleType);
    List<UserResponseDto> findUsersByRole(RoleType roleType);
    Boolean hasRole(Long userId, RoleType roleType);
    Boolean isRoleHigher(RoleType roleType1, RoleType roleType2);
    UserRoleResponseDto assignRole(Long userId, RoleType roleType);
    Set<RoleType> getRolesByUserId(Long userId);

}