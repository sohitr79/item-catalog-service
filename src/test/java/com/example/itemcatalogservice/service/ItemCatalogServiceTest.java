package com.example.itemcatalogservice.service;

import com.example.itemcatalogservice.model.Item;
import com.example.itemcatalogservice.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemCatalogServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemCatalogService itemCatalogService;

    private Item item;

    @BeforeEach
    public void init(){
        item = new Item(123,"Sofa","furniture",4000);
    }

    @Test
    public void itemCatalogServiceSaveItem(){
        when(itemRepository.save(Mockito.any(Item.class))).thenReturn(item);

        Item savedItem = itemCatalogService.saveItem(item);

        Assertions.assertEquals(item.getName(),savedItem.getName());
        Assertions.assertEquals(item.getPrice(),savedItem.getPrice());
    }
}