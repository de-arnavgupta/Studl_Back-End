package de.arnav.studl.facade.Implementation;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.dto.userDto.UserUpdateDto;
import de.arnav.studl.facade.UserFacade;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.service.implementation.UserServiceImpl;

import java.util.List;

public class UserFacadeImpl implements UserFacade {

    private final UserServiceImpl userService;

    public UserFacadeImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserResponseDto update(UserUpdateDto userUpdateDto, Long userId) {
        return userService.updateUser(userUpdateDto, userId);
    }

    @Override
    public UserResponseDto removeRole(Long userRoleId, RoleType roleType) {
        return userService.removeRoleFromUser(userRoleId, roleType);
    }

    @Override
    public UserResponseDto removeAllRoles(Long userRoleId) {
        return userService.removeAllRolesFromUser(userRoleId);
    }

    @Override
    public UserResponseDto findById(Long userId) {
        return userService.findUserById(userId);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    @Override
    public List<UserResponseDto> findByUsername(String username) {
        return userService.findUsersByUsername(username);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userService.findAllUsers();
    }

    @Override
    public OrganizationResponseDto findOrganizationById(Long userId) {
        return userService.findOrganizationByUserId(userId);
    }

    @Override
    public Boolean hasRole(Long userId, RoleType roleType) {
        return userService.userHasRole(userId, roleType);
    }
}
