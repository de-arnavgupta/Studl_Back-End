package de.arnav.studl.service;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(User user);
    UserResponseDto updateUser(User user, Long userId);
    void deleteUser(Long userId);
    void lockUserDueToFailedAttempts(Long userId);
    UserResponseDto findUserById(Long userId);
    UserResponseDto findUserByEmail(String email);
    List<UserResponseDto> findUsersByUsername(String username);
    List<UserResponseDto> findAllUsers();
    RoleType findRoleByUsername(String username);
    OrganizationResponseDto findOrganizationByUsername(String username);
    Integer getFailedLoginAttemptCount(Long userId);
    Boolean userHasRole(Long userId, String roleName);
    Boolean isUserHigherInHierarchy(Long user1Id, Long user2Id);
    Boolean isAccountLocked(Long userId);

}
