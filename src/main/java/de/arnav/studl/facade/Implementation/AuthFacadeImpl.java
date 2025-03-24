package de.arnav.studl.facade.Implementation;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.facade.AuthFacade;
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
    public UserResponseDto userRegister(UserCreateDto userCreateDto) {
        authService.verifyOrganization(userCreateDto.getEmail());
        UserResponseDto userResponseDto = userService.createUser(userCreateDto);
        jwtService.generateToken(userCreateDto.getEmail());
        return userResponseDto;
    }

    @Override
    public String userLogin(String email,String password) {
        authService.verifyOrganization(email);
        authService.authenticateUser(email, password);
       return jwtService.generateToken(email);
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        authService.logout(request);
    }
}