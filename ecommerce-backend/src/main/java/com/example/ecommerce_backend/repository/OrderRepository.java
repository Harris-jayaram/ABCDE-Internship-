package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.Order;
import com.example.ecommerce_backend.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}