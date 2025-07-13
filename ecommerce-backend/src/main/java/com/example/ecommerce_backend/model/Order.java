package com.example.ecommerce_backend.model;
import jakarta.persistence.*;
import java.util.List;

import jakarta.persistence.*;
     import java.util.List;

     @Entity
     public class Order {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @ManyToOne
         @JoinColumn(name = "user_id")
         private User user;

         private Long cartId;

         @ManyToMany
         @JoinTable(
             name = "order_items",
             joinColumns = @JoinColumn(name = "order_id"),
             inverseJoinColumns = @JoinColumn(name = "item_id")
         )
         private List<Item> items;

         // Getters and Setters
         public Long getId() { return id; }
         public void setId(Long id) { this.id = id; }
         public User getUser() { return user; }
         public void setUser(User user) { this.user = user; }
         public Long getCartId() { return cartId; }
         public void setCartId(Long cartId) { this.cartId = cartId; }
         public List<Item> getItems() { return items; }
         public void setItems(List<Item> items) { this.items = items; }
     }