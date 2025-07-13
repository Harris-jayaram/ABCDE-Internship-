package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.model.Cart;
import com.example.ecommerce_backend.model.Order;
import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.repository.CartRepository;
import com.example.ecommerce_backend.repository.OrderRepository;
import com.example.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Long userId, Long cartId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        if (!cart.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        Order order = new Order();
        order.setUser(user);
        order.setCartId(cartId);
        order.setItems(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);
        return orderRepository.save(order);
    }

    public List<Order> findByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return orderRepository.findByUser(user);
    }
}