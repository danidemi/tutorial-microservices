package com.danidemi.tutorial.microservices.springboot.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.danidemi.tutorial.microservices.springboot.rest.controller.json.Item;
import com.danidemi.tutorial.microservices.springboot.rest.controller.json.NewItem;

@RestController
public class ItemController {

    private final Map<UUID, Item> itemsById;

    public ItemController() {
        this.itemsById = new HashMap<>();
        postItem(new NewItem("item #1"));
    }

    @RequestMapping(value="/items", method = RequestMethod.POST)
    public Item postItem(@RequestBody NewItem newItem){
        Item item = new Item(newItem);
        item.setId( UUID.randomUUID() );
        itemsById.put( item.getId(), item );
        return item;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    List<Item> getItems() {
        return new ArrayList<>( itemsById.values() );
    }

    @RequestMapping(value = "/items/{item}", method = RequestMethod.DELETE)
    public Item deleteItem(@PathVariable String item) {
        return itemsById.remove(UUID.fromString(item));
    }

}
