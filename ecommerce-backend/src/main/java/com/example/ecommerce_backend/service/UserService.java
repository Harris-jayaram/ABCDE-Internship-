package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setToken(UUID.randomUUID().toString()); // New token invalidates old one
            return userRepository.save(user);
        }
        return null;
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }
}