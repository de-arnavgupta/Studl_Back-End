package de.arnav.studl.controller;

import de.arnav.studl.dto.organizationDto.OrganizationResponseDto;
import de.arnav.studl.dto.userDto.UserResponseDto;
import de.arnav.studl.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/fetchBy/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@Valid @PathVariable String email) {
        UserResponseDto user = userFacade.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/fetchOrganizationBy/{email}")
    public ResponseEntity<OrganizationResponseDto> getOrganizationByUserEmail(@Valid @PathVariable String email) {
        OrganizationResponseDto organization = userFacade.findOrganizationByEmail(email);
        return ResponseEntity.ok(organization);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userFacade.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/")
    public String getUserByEmail() {
        return "Hello World";
    }

}
