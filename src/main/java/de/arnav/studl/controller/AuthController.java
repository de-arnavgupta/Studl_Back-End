package de.arnav.studl.controller;

import de.arnav.studl.dto.authDto.LoginRequest;
import de.arnav.studl.dto.userDto.UserCreateDto;
import de.arnav.studl.facade.AuthFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade){
        this.authFacade = authFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequest loginRequest){
            String token=authFacade.userLogin(loginRequest.getEmail(),loginRequest.getPassword());

            return ResponseEntity.ok(Map.of("token",token));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
       authFacade.userLogout(request);
       return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody UserCreateDto user){
         String token = authFacade.userRegister(user);
         return ResponseEntity.ok(Map.of("token",token));
    }

}
