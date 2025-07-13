package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.Order;
import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.service.OrderService;
import com.example.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestHeader("Authorization") String token, @RequestBody Map<String, Long> body) {
        User user = userService.findByToken(token);
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(orderService.createOrder(user.getId(), body.get("cartId")));
    }

    @GetMapping
    public ResponseEntity<List<Order>> listOrders(@RequestHeader("Authorization") String token) {
        User user = userService.findByToken(token);
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(orderService.findByUser(user.getId()));
    }
}