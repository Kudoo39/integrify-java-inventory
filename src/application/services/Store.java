package application.services;

import domain.abstractions.INotificationService;
import domain.entities.Item;
import domain.enums.SortOrder;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private INotificationService notificationService;
    private List<Item> items;
    private int capacity;

    public Store(int capacity, INotificationService notificationService) {
        this.items = new ArrayList<>();
        this.capacity = capacity;
        this.notificationService = notificationService;
    }

    public void addItem(Item item) {
        if (findItemByName(item.getName()) == null) {
            if (getCurrentVolume() + item.getQuantity() <= capacity) {
                items.add(item);
                notificationService.sendNotificationOnSuccess(item, "added to");
            } else {
                System.out.println("Cannot add " + item.getName() + ", please upgrade your storage!");
                notificationService.sendNotificationOnFailure(item, "adding");
            }
        } else {
            System.out.println(item.getName() + " is already existed!");
            notificationService.sendNotificationOnFailure(item, "adding");
        }
    }

    public void remove(Item item) {
        items.remove(item);
        notificationService.sendNotificationOnSuccess(item, "removed from");
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

    public Map<String, List<Item>> groupByDate() {
        Map<String, List<Item>> groupItems = new HashMap<>();

        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsDate = currentDate.minusMonths(3);

        List<Item> newArrivals = new ArrayList<>();
        List<Item> oldItems = new ArrayList<>();

        for (Item item : items) {
            if (item.getDate().isAfter(threeMonthsDate) || item.getDate().isEqual(threeMonthsDate)) {
                newArrivals.add(item);
            } else {
                oldItems.add(item);
            }
        }

        groupItems.put("New Arrival", newArrivals);
        groupItems.put("Old", oldItems);

        return groupItems;
    }

    public void displayItems() {
        for (Item item : items) {
            System.out.println("Item: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Date: " + item.getDate());
        }
    }
}
