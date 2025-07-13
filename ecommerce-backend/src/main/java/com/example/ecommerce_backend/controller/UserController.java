package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User authenticatedUser = userService.login(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(new LoginResponse(authenticatedUser.getToken()));
        }
        return ResponseEntity.status(401).body("Invalid username/password");
    }
}

class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}