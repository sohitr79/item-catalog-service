package com.example.itemcatalogservice.controller;


import com.example.itemcatalogservice.model.Item;
import com.example.itemcatalogservice.service.ItemCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemCatalogController {

    @Autowired
    ItemCatalogService itemCatalogService;

    @PostMapping("/save")
    public ResponseEntity<Item> saveItem(@RequestBody Item item){
        Item savedItem = itemCatalogService.saveItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Integer id){
       return new ResponseEntity<>(itemCatalogService.getItem(id),HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Integer id,@RequestBody Map<String,Object> updates){
        Item updatedItem=itemCatalogService.updateItemPartial(id, updates);
        return new ResponseEntity<>(updatedItem,HttpStatus.OK);
    }
}
