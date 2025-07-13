package com.example.ecommerce_backend.model;
import jakarta.persistence.*;
import java.util.List;

  import jakarta.persistence.*;
     import java.util.List;

     @Entity
     public class Cart {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @OneToOne
         @JoinColumn(name = "user_id", unique = true)
         private User user;

         @ManyToMany
         @JoinTable(
             name = "cart_items",
             joinColumns = @JoinColumn(name = "cart_id"),
             inverseJoinColumns = @JoinColumn(name = "item_id")
         )
         private List<Item> items;

         // Getters and Setters
         public Long getId() { return id; }
         public void setId(Long id) { this.id = id; }
         public User getUser() { return user; }
         public void setUser(User user) { this.user = user; }
         public List<Item> getItems() { return items; }
         public void setItems(List<Item> items) { this.items = items; }
     }