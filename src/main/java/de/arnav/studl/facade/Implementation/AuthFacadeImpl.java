package de.arnav.studl.facade.Implementation;

import de.arnav.studl.facade.AuthFacade;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.User;
import de.arnav.studl.security.service.AuthService;
import de.arnav.studl.security.service.JwtService;
import de.arnav.studl.service.implementation.OrganizationServiceImpl;
import de.arnav.studl.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthFacadeImpl implements AuthFacade {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final OrganizationServiceImpl organizationService;

    public AuthFacadeImpl(AuthService authService, JwtService jwtService, UserServiceImpl userService, OrganizationServiceImpl organizationService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.userService = userService;
        this.organizationService = organizationService;
    }

    @Override
    public void organizationRegister(Organization organization) {
        organizationService.createOrganization(organization);
    }

    @Override
    public String userRegister(User user) {
        authService.verifyOrganization(user.getEmail());
        userService.createUser(user);
        return jwtService.generateToken(user.getEmail());
    }

    @Override
    public void userLogin(User user) {
        authService.authenticateUser(user.getEmail(), user.getPassword());
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        authService.logout(request);
    }
}

/*
- Logout needs refresh tokens or token blacklisting.

- Using the following models:
User, Organization
 */