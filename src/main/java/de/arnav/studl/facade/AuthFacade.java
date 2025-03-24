package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.model.Organization;
import de.arnav.studl.model.User;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthFacade {

    // methods order: organisation, user, role, email
    OrganizationResponseDto organizationRegister (OrganizationCreateDto organizationCreateDto);
    String userRegister (User user);
    void userLogin (User user);
    void userLogout (HttpServletRequest request);

}

/*
- Logout needs refresh tokens or token blacklisting.

- Using the following models:
User, Organization
 */