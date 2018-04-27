package com.danidemi.tutorial.microservices.springboot.rest.controller;

import com.danidemi.tutorial.microservices.springboot.rest.json.Item;
import com.danidemi.tutorial.microservices.springboot.rest.json.NewItem;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
