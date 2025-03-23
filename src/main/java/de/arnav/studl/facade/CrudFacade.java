package de.arnav.studl.facade;

import de.arnav.studl.model.Organization;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;

import java.util.List;

public interface CrudFacade {

    void createUser(User user);
    void updateUser(User user, Long userId);
    void deleteUser(Long userId);
    User findUserById(Long userId);
    User findUserByEmail(String email);
    List<User> findUsersByUsername(String username);
    List<User> findUsersByRole(RoleType roleType);
    List<User> findAllUsers();
    RoleType findRoleByUserId(Long userId);
    RoleType findRoleByUsername(String username);
    Boolean hasRole(Long userId, RoleType roleType);
    void createOrganization(Organization organization);
    void updateOrganization(Organization organization, Long organizationId);
    void deleteOrganization(Long organizationId);
    Organization findOrganizationById(Long organizationId);
    List<Organization> findAllOrganizations();
    List<Organization> findOrganizationsByName(String name);

}