package services;

import model.Item;
import util.SortOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Store {
    private List<Item> items;
    private int capacity;

    public Store(int capacity) {
        this.items = new ArrayList<>();
        this.capacity = capacity;
    }

    public void addItem(Item item) {
        if (findItemByName(item.getName()) == null) {
            if (getCurrentVolume() + item.getQuantity() <= capacity) {
                items.add(item);
            } else {
                System.out.println("Cannot add " + item.getName() + ", please upgrade your storage!");
            }
        } else {
            System.out.println(item.getName() + " is already existed!");
        }
    }

    public void remove(Item item) {
        items.remove(item);
    }

   public int getCurrentVolume() {
        int total = 0;
        for (Item item : items) {
            total = total + item.getQuantity();
        }
        return total;
   }

    public Item findItemByName(String name) {
        for (Item item : items) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    public List<Item> sortByNameAsc() {
        items.sort((Item item1, Item item2) -> item1.getName().compareTo(item2.getName()));
        return items;
    }

    public List<Item> sortByDate(SortOrder order) {
        if (order == SortOrder.ASC) {
            Collections.sort(items, Comparator.comparing(Item::getDate));
        } else {
            Collections.sort(items, Comparator.comparing(Item::getDate).reversed());
        }
        return items;
    }

    public void displayItems() {
        for (Item item : items) {
            System.out.println("Item: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Date: " + item.getDate());
        }
    }
}
