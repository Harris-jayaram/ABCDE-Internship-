package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.Item;
import com.example.ecommerce_backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.createItem(item));
    }
    @GetMapping
    public ResponseEntity<List<Item>> listItems() {
        return ResponseEntity.ok(itemService.findAll());
    }
}