package de.arnav.studl.service;

import de.arnav.studl.model.Organization;
import de.arnav.studl.model.Role;
import de.arnav.studl.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    void updateUser(User user, Long userId);
    void deleteUser(Long userId);
    void lockUserDueToFailedAttempts(Long userId);
    User findUserById(Long userId);
    User findUserByEmail(String email);
    List<User> findUsersByUsername(String username);
    List<User> findAllUsers();
    Role findRoleByUsername(String username);
    Organization findOrganizationByUsername(String username);
    Integer getFailedLoginAttemptCount(Long userId);
    Boolean userHasRole(Long userId, String roleName);
    Boolean isUserHigherInHierarchy(Long user1Id, Long user2Id);
    Boolean isAccountLocked(Long userId);

}
