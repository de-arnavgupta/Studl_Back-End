package de.arnav.studl.controller;

import de.arnav.studl.dto.userRole.UserRoleCreateDto;
import de.arnav.studl.dto.userRole.UserRoleResponseDto;
import de.arnav.studl.facade.template.UserRoleFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleFacade userRoleFacade;

    public UserRoleController(UserRoleFacade userRoleFacade) {
        this.userRoleFacade = userRoleFacade;
    }

    @PostMapping
    public ResponseEntity<UserRoleResponseDto> assignRole(@RequestBody UserRoleCreateDto dto) {
        UserRoleResponseDto response = userRoleFacade.assignRole(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleResponseDto>> getUserRolesByUser(@PathVariable Long userId) {
        List<UserRoleResponseDto> responses = userRoleFacade.getUserRolesByUser(userId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable Long id) {
        userRoleFacade.removeRoleFromUser(id);
        return ResponseEntity.noContent().build();
    }
}