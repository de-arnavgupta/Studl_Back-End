package de.arnav.studl.service;

import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userRoleDto.UserRoleCreateDto;
import de.arnav.studl.dto.userRoleDto.UserRoleResponseDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.HashSet;
import java.util.List;

public interface UserRoleService {

    Integer countUsersByRole(RoleType roleType);
    HashSet<RoleType> findRolesByUserId(Long userId);
    RoleType findNextRole(RoleType roleType);
    RoleType findPrevRole(RoleType roleType);
    List<UserResponseDto> findUsersByRole(RoleType roleType);
    Boolean hasRole(Long userId, RoleType roleType);
    Boolean isRoleHigher(RoleType roleType1, RoleType roleType2);
    UserRoleResponseDto assignRole(Long userId, RoleType roleType);
    HashSet<RoleType> getRolesByUserId(Long userId);
//    void removeRoleFromUser(Long userRoleId, RoleType roleType);
//    void removeAllRolesFromUser(Long userRoleId);

}