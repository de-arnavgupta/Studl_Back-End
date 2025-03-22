package de.arnav.studl.facade.authFacade;

import de.arnav.studl.model.Organization;
import de.arnav.studl.model.User;

public interface AuthFacade {
    // methods: userRegister, userLogin, userLogout and organisationRegister
    void organizationRegister (Organization organization);
    void userRegister (User user);
    void userLogin (User user);
    // void userLogout (User user);
}

/*
- Logout needs refresh tokens or token blacklisting.

- Using the following models:
User, Organization
 */