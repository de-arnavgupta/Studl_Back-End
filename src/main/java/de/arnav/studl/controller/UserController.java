package de.arnav.studl.controller;

import de.arnav.studl.dto.userDto.UserDeleteDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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

    @GetMapping("fetchUser/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable @Valid String email) {
        UserResponseDto user = userFacade.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public String getUserByEmail() {
        return "Hello World";
    }

}
