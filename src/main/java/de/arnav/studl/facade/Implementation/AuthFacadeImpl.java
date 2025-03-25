package de.arnav.studl.facade.Implementation;

import de.arnav.studl.dto.organizationDto.OrganizationCreateDto;
import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.exception.JwtAuthenticationException;
import de.arnav.studl.facade.AuthFacade;
import de.arnav.studl.security.service.AuthService;
import de.arnav.studl.security.service.JwtService;
import de.arnav.studl.service.implementation.OrganizationServiceImpl;
import de.arnav.studl.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthFacadeImpl implements AuthFacade {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    public AuthFacadeImpl(AuthService authService, JwtService jwtService, UserServiceImpl userService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public UserResponseDto userRegister(UserCreateDto userCreateDto) {
        if(!authService.verifyOrganization(userCreateDto.getEmail())) {
            throw new JwtAuthenticationException();
        }
        UserResponseDto userResponseDto = userService.createUser(userCreateDto);
        jwtService.generateToken(userCreateDto.getEmail());
        return userResponseDto;
    }

    @Override
    public String userLogin(String email,String password) {
        if(!authService.verifyOrganization(email)) {
            throw new JwtAuthenticationException();
        }
        authService.authenticateUser(email, password);
       return jwtService.generateToken(email);
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        authService.logout(request);
    }
}