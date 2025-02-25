package com.example.itemcatalogservice.controller;

import com.example.itemcatalogservice.model.Item;
import com.example.itemcatalogservice.service.ItemCatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ItemCatalogController.class)
@ExtendWith(MockitoExtension.class)
class ItemCatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemCatalogService itemCatalogService;

    @Autowired
    ObjectMapper objectMapper;

    private Item item;

    @BeforeEach
    public void init(){
        item = new Item(123,"Sofa","furniture",4000);
    }

    @Test
    public void itemCatalogControllerSaveItem() throws Exception {
        given(itemCatalogService
                .saveItem(ArgumentMatchers.any()))
                .willAnswer((invocation->invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/item/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)));

        response
                .andExpect(MockMvcResultMatchers.status().isCreated()) // will match response status
                .andDo(MockMvcResultHandlers.print()) // will print response on console
                // will compare individual fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(item.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(item.getPrice())));
    }

    @Test
    public void itemCatalogControllerGetItem() throws Exception {
        int id =123;

        when(itemCatalogService.getItem(id)).thenReturn(item);

        ResultActions response = mockMvc.perform(get("/item/123")
                .contentType(MediaType.APPLICATION_JSON));

        response
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}