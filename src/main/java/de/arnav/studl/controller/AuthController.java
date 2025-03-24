package de.arnav.studl.controller;

import de.arnav.studl.dto.LoginRequest;
import de.arnav.studl.dto.UserDeleteDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade){
        this.authFacade = authFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequest loginRequest){
            String token=authFacade.loginUser(loginRequest.getEmail(),loginRequest.getPassword());

            return ResponseEntity.ok(Map.of("token",token));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
       authFacade.logoutUser(request);
       return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
         authFacade.registerUser(user);
         return ResponseEntity.ok("User registered successfully");
    }

}
