package com.example.demo.controllers.rest;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Validated User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PatchMapping("/{username}/reset-password")
    public ResponseEntity<User> resetPassword(@PathVariable String username, @RequestParam String newPassword) {
        return ResponseEntity.ok(userService.resetPassword(username, newPassword));
    }

    @PatchMapping("/{username}/toggle-unlocked")
    public ResponseEntity<User> toggleUnlocked(@PathVariable String username) {
        return ResponseEntity.ok(userService.toggleUnlocked(username));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}