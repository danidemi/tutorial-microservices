package ms.rest.boot.server.resources;

import java.util.Objects;
import java.util.UUID;

public class Item {

    private UUID id;
    private String description;

    public Item() {
        super();
    }

    public Item(String description) {
        this.description = description;
    }

    public Item(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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
}
