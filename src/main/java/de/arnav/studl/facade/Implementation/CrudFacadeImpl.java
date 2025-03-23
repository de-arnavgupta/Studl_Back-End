package de.arnav.studl.facade.Implementation;

import de.arnav.studl.facade.CrudFacade;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.Role;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.service.UserRoleService;
import de.arnav.studl.service.implementation.OrganizationServiceImpl;
import de.arnav.studl.service.implementation.UserServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrudFacadeImpl implements CrudFacade {

    private final UserServiceImpl userService;
    private final OrganizationServiceImpl organizationService;
    private final UserRoleService userRoleService;

    public CrudFacadeImpl(UserServiceImpl userService, OrganizationServiceImpl organizationService, UserRoleService userRoleService) {
        this.userService = userService;
        this.organizationService = organizationService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void createUser(User user) {
        userService.createUser(user);
    }

    @Override
    public void updateUser(User user, Long userId) {
        userService.updateUser(user, userId);
    }

    @Override
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userService.findUserById(userId);
    }

    @Override
    public List<User> findUsersByUsername(String username) {
        return userService.findUsersByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    @Override
    public List<User> findUsersByRole(RoleType roleType) {
        return userRoleService.findUsersByRole(roleType);
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public Role findRoleByUserId(Long userId) {
        return userRoleService.findRoleByUserId(userId);
    }

    @Override
    public Role findRoleByUsername(String username) {
        return userService.findRoleByUsername(username);
    }

    @Override
    public Boolean hasRole(Long userId, RoleType roletype) {
        return userRoleService.hasRole(userId, roletype);
    }

    @Override
    public void createOrganization(Organization organization) {
        organizationService.createOrganization(organization);
    }

    @Override
    public void updateOrganization(Organization organization, Long organizationId) {
        organizationService.updateOrganization(organization, organizationId);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationService.deleteOrganization(organizationId);
    }

    @Override
    public List<Organization> findAllOrganizations() {
        return organizationService.findAllOrganizations();
    }

    @Override
    public Organization findOrganizationById(Long organizationId) {
        return organizationService.findOrganizationById(organizationId);
    }

    @Override
    public List<Organization> findOrganizationsByName(String name) {
        return organizationService.findOrganizationsByName(name);
    }
}
