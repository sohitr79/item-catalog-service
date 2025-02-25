package com.example.itemcatalogservice.service;

import com.example.itemcatalogservice.model.Item;
import com.example.itemcatalogservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemCatalogService {

    @Autowired
    ItemRepository repository;

    public Item saveItem(Item item) {
        return repository.save(item);
    }

    public Item getItem(Integer id) {
        return repository.getReferenceById(id);
    }

    public Item updateItemPartial(Integer id, Map<String, Object> updates) {
        Item item = getItem(id);
        return saveItem(updatedItem(item,updates));

    }

    private Item updatedItem(Item item, Map<String, Object> updates) {

        Integer id = item.getId();
        String name = updates.containsKey("name") ? (String) updates.get("name") : item.getName();
        String category = updates.containsKey("category") ? (String) updates.get("category") : item.getCategory();
        Integer price = updates.containsKey("price") ? (Integer) updates.get("price") : item.getPrice();
        return new Item(id, name, category, price);
    }
}
