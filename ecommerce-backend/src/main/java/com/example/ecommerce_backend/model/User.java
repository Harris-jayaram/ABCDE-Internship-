package com.example.ecommerce_backend.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


     import jakarta.persistence.*;
     import java.util.List;

     @Entity
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
         @Column(unique = true)
         private String username;
         private String password;
         private String token;

         @OneToOne(mappedBy = "user")
         private Cart cart;

         @OneToMany(mappedBy = "user")
         private List<Order> orders;

         // Getters and Setters
         public Long getId() { return id; }
         public void setId(Long id) { this.id = id; }
         public String getUsername() { return username; }
         public void setUsername(String username) { this.username = username; }
         public String getPassword() { return password; }
         public void setPassword(String password) { this.password = password; }
         public String getToken() { return token; }
         public void setToken(String token) { this.token = token; }
     }