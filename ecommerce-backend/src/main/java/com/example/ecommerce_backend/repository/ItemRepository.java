package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}