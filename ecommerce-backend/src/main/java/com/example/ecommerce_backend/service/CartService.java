package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.model.Cart;
import com.example.ecommerce_backend.model.Item;
import com.example.ecommerce_backend.model.User;
import com.example.ecommerce_backend.repository.CartRepository;
import com.example.ecommerce_backend.repository.ItemRepository;
import com.example.ecommerce_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Cart addToCart(Long userId, Long itemId) {
        User user = userRepository.findById(userId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }
}