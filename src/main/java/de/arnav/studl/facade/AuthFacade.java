package de.arnav.studl.facade;

import de.arnav.studl.dto.userDto.UserCreateDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthFacade {

    // methods order: organisation, user, role, email
    String userRegister (UserCreateDto userCreateDto);
    String userLogin (String email,String password);
    void userLogout (HttpServletRequest request);

}