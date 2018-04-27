package com.danidemi.tutorial.microservices.springboot.rest.json;

import java.util.Objects;

public class NewItem {

    private String description;

    public NewItem() {
    }

    public NewItem(String description) {
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NewItem newItem = (NewItem) o;
        return Objects.equals(description, newItem.description);
    }

    @Override public int hashCode() {

        return Objects.hash(description);
    }
}
