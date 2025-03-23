package de.arnav.studl.controller;

import de.arnav.studl.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try{
            String token=authFacade.loginUser(loginRequest.getEmail(),loginRequest.getPassword());

            return ResponseEntity.ok(Map.of("token",token));
        } catch(Exception e){
            return ResponseEntity.status(401).body(Map.of("error",e.getMessage()));
        }
    }

    @PostMapping("/register")


}
