package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.model.Item;
import com.example.ecommerce_backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}