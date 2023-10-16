package com.example.rest.controller;

import com.example.rest.model.Item;
import com.example.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<Optional<List<Item>>> getItems() {

        Optional<List<Item>> itemList = itemService.findAll();

        return ResponseEntity.ok(itemList);

    }

    @GetMapping("/items/{code}")
    public ResponseEntity<Item> getItemByCode(@PathVariable String code) {

        Item item = itemService.findByCode(code);

        return ResponseEntity.ok(item);

    }

    @DeleteMapping("/items")
    public ResponseEntity deleteAllItems() {
        itemService.deleteAll();
        return ResponseEntity.ok("All items are deleted.");
    }


}
