package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthFacade {

    // methods order: organisation, user, role, email
    OrganizationResponseDto organizationRegister (OrganizationCreateDto organizationCreateDto);
    UserResponseDto userRegister (UserCreateDto userCreateDto);
    void userLogin (User user);
    void userLogout (HttpServletRequest request);

}