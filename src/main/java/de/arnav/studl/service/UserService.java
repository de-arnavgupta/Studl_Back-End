package de.arnav.studl.service;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserDeleteDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.model.RoleType;

import java.util.List;

public interface UserService {

    void deleteUser(UserDeleteDto userDeleteDto);
    UserResponseDto createUser(UserCreateDto userCreateDto);
    UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId);
    UserResponseDto removeRoleFromUser(Long userRoleId, RoleType roleType);
    UserResponseDto removeAllRolesFromUser(Long userRoleId);
    UserResponseDto findUserById(Long userId);
    UserResponseDto findUserByEmail(String email);
    List<UserResponseDto> findUsersByUsername(String username);
    List<UserResponseDto> findAllUsers();
    OrganizationResponseDto findOrganizationByUserId(Long userId);
    Boolean userHasRole(Long userId, RoleType roleType);

}
