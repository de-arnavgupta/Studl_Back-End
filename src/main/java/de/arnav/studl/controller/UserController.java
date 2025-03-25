package de.arnav.studl.controller;

import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable String email) {
        userFacade.delete(email);
        return ResponseEntity.ok("✅ User deleted successfully");
    }

    @GetMapping("fetchBy/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@Valid @PathVariable String email) {
        UserResponseDto user = userFacade.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public String getUserByEmail() {
        return "Hello World";
    }

}
