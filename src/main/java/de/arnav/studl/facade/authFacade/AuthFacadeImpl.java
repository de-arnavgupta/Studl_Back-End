package de.arnav.studl.facade.authFacade;

import de.arnav.studl.model.Organization;
import de.arnav.studl.model.User;
import de.arnav.studl.service.implementation.OrganizationServiceImpl;
import de.arnav.studl.service.implementation.UserServiceImpl;

public class AuthFacadeImpl implements AuthFacade {

    private final UserServiceImpl userService;
    private final OrganizationServiceImpl organizationService;

    public AuthFacadeImpl(UserServiceImpl userService, OrganizationServiceImpl organizationService) {
        this.userService = userService;
        this.organizationService = organizationService;
    }

    @Override
    public void organizationRegister(Organization organization) {
        organizationService.createOrganization(organization);
    }

    @Override
    public void userRegister(User user) {
        userService.createUser(user);
    }

    @Override
    public void userLogin(User user) {
        userService.verifyUser(user);
    }

//    @Override
//    public void userLogout(User user) {
//
//    }
}

/*
- Logout needs refresh tokens or token blacklisting.

- Using the following models:
User, Organization
 */