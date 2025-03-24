package de.arnav.studl.controller;

import de.arnav.studl.dto.UserDeleteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private User user;
    private UserFacade userFacade;

    public UserController(User user ,UserFacade userFacade) {
        this.user = user;
        this.userFacade = userFacade;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteDto request) {
        userFacade.deleteUser(request.getToken());
        return ResponseEntity.ok("✅ User deleted successfully");
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userFacade.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

}
