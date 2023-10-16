package com.example.rest.service;

import com.example.rest.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {
    Optional<List<Item>> findAll();

    Item findByCode(String code);
}
