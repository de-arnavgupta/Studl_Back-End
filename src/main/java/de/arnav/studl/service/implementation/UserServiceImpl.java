package de.arnav.studl.service.implementation;

import de.arnav.studl.model.Organization;
import de.arnav.studl.model.Role;
import de.arnav.studl.model.User;
import de.arnav.studl.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public void verifyUser(User user) {

    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user, Long userId) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void lockUserDueToFailedAttempts(Long userId) {

    }

    @Override
    public User findUserById(Long userId) {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public Role findRoleByUsername(String username) {
        return null;
    }

    @Override
    public Organization findOrganizationByUsername(String username) {
        return null;
    }

    @Override
    public Integer getFailedLoginAttemptCount(Long userId) {
        return 0;
    }

    @Override
    public Boolean userHasRole(Long userId, String roleName) {
        return false;
    }

    @Override
    public Boolean isUserHigherInHierarchy(Long user1Id, Long user2Id) {
        return false;
    }

    @Override
    public Boolean isAccountLocked(Long userId) {
        return false;
    }
}
