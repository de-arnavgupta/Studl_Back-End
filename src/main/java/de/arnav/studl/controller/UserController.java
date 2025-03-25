package de.arnav.studl.controller;

import de.arnav.studl.dto.userDto.UserDeleteDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.facade.UserFacade;
import de.arnav.studl.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteDto request) {
        userFacade.delete(request);
        return ResponseEntity.ok("✅ User deleted successfully");
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        UserResponseDto user = userFacade.findByEmail(email);
        return ResponseEntity.ok(user);
    }

}
