package de.arnav.studl.service;

import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userRoleDto.UserRoleCreateDto;
import de.arnav.studl.dto.userRoleDto.UserRoleResponseDto;
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
    List<UserResponseDto> findUsersByRole(RoleType roleType);
    Boolean hasRole(Long userId, RoleType roleType);
    Boolean isRoleHigher(RoleType roleType1, RoleType roleType2);
    UserRoleResponseDto assignRole(UserRoleCreateDto dto);
    List<UserRoleResponseDto> getUserRolesByUser(Long userId);
    void removeRoleFromUser(Long userRoleId);

}