package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.Cart;
import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.service.CartService;
import com.example.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestHeader("Authorization") String token, @RequestBody Map<String, Long> body) {
        User user = userService.findByToken(token);
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(cartService.addToCart(user.getId(), body.get("itemId")));
    }

  @GetMapping
    public ResponseEntity<List<Cart>> listCarts() {
        return ResponseEntity.ok(cartService.findAll());
    }
    
    
}