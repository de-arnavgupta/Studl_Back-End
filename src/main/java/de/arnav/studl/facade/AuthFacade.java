package de.arnav.studl.facade;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthFacade {

    // methods order: organisation, user, role, email
    UserResponseDto userRegister (UserCreateDto userCreateDto);
    String userLogin (String email,String password);
    void userLogout (HttpServletRequest request);

}