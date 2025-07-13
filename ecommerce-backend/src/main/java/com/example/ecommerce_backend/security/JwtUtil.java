package com.example.ecommerce_backend.security;


import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Autowired
    private UserService userService;

    public User validateToken(String token) {
        return userService.findByToken(token);
    }
}