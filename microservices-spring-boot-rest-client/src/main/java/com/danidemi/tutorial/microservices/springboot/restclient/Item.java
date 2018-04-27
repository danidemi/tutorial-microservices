package com.danidemi.tutorial.microservices.springboot.restclient;

import java.util.Objects;
import java.util.UUID;

public class Item extends NewItem {

    private UUID id;

    public Item(NewItem newItem) {
        super();
        this.setDescription(newItem.getDescription());
    }

    public Item() {
        super();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("id=").append(id);
        sb.append(", description=").append(getDescription());
        sb.append('}');
        return sb.toString();
    }
}
