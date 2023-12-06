package worldofzult.domain.inventory;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<Item>(); // ArrayList containing items

    // Get item by name
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) return item;
        }
        return null;
    }

    // Add item to inventory
    public void addItem(Item item){
        items.add(item);
    }

    // Remove item from inventory
    public void removeItem(Item item){
        items.remove(item);
    }

    // Get ArrayList of items or rather the whole inventory
    public ArrayList<Item> getItems() {
        return items;
    }
}