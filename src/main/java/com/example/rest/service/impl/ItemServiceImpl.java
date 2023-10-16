package com.example.rest.service.impl;

import com.example.rest.model.Item;
import com.example.rest.repository.ItemRepository;
import com.example.rest.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Item>> findAll() {
        List<Item> itemList = repository.findAll();

        return Optional.ofNullable(itemList);
    }
}
