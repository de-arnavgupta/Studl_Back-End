package de.arnav.studl.service.implementation;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDto createUser(User user) {
        return null;
    }

    @Override
    public UserResponseDto updateUser(User user, Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void lockUserDueToFailedAttempts(Long userId) {

    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        return null;
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserResponseDto> findUsersByUsername(String username) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return List.of();
    }

    @Override
    public RoleType findRoleByUsername(String username) {
        return null;
    }

    @Override
    public OrganizationResponseDto findOrganizationByUsername(String username) {
        return null;
    }

    @Override
    public Integer getFailedLoginAttemptCount(Long userId) {
        return 0;
    }

    @Override
    public Boolean userHasRole(Long userId, String roleName) {
        return null;
    }

    @Override
    public Boolean isUserHigherInHierarchy(Long user1Id, Long user2Id) {
        return null;
    }

    @Override
    public Boolean isAccountLocked(Long userId) {
        return null;
    }
}
