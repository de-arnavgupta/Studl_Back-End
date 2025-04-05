package de.arnav.studl.controller;

import de.arnav.studl.dto.user.UserCreateDto;
import de.arnav.studl.dto.user.UserResponseDto;
import de.arnav.studl.dto.user.UserUpdateDto;
import de.arnav.studl.facade.template.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto dto) {
        UserResponseDto response = userFacade.createUser(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto response = userFacade.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam("value") String email) {
        UserResponseDto response = userFacade.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> responses = userFacade.getAllUsers();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        UserResponseDto response = userFacade.updateUser(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<UserResponseDto>> getUsersByOrganization(@PathVariable Long organizationId) {
        List<UserResponseDto> responses = userFacade.getUsersByOrganization(organizationId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> findUsersByName(@RequestParam("keyword") String keyword) {
        List<UserResponseDto> responses = userFacade.findUsersByName(keyword);
        return ResponseEntity.ok(responses);
    }
}