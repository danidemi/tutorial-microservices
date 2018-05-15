package ms.rest.boot.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import ms.rest.boot.server.resources.Item;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final Map<UUID, Item> itemsById;

    public ItemController() {
        this.itemsById = new HashMap<>();

        // pre populating some items
        postItem(new Item("item #1"));
        postItem(new Item("item #2"));
        postItem(new Item("item #3"));
        postItem(new Item("item #4"));
    }

    @PostMapping()
    public Item postItem(@RequestBody Item item){
        item.setId( UUID.randomUUID() );
        itemsById.put( item.getId(), item );
        return item;
    }

    @GetMapping()
    List<Item> getItems() {
        return new ArrayList<>( itemsById.values() );
    }

    @DeleteMapping("/{itemId}")
    public Item deleteItem(@PathVariable String itemId) {
        return itemsById.remove(UUID.fromString(itemId));
    }

}
