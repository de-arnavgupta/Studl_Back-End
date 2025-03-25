package de.arnav.studl.facade.Implementation;

import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.exception.InvalidCredentialsException;
import de.arnav.studl.facade.AuthFacade;
import de.arnav.studl.security.service.AuthService;
import de.arnav.studl.security.service.JwtService;
import de.arnav.studl.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
    public String userRegister(UserCreateDto userCreateDto) {
        if(!authService.verifyOrganization(userCreateDto.getEmail())) {
            throw new InvalidCredentialsException("Invalid email or password. [Method: userRegister]");
        }
        userService.createUser(userCreateDto);
        return jwtService.generateToken(userCreateDto.getEmail());
    }

    @Override
    public String userLogin(String email,String password) {
        if(!authService.verifyOrganization(email)) {
            throw new InvalidCredentialsException("Invalid email or password. [Method: userLogin]");
        }
       return  authService.authenticateUser(email, password);
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        authService.logout(request);
    }
}