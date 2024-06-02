import model.Item;
import services.Store;
import util.SortOrder;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Store store = new Store(100);

        Item chocolateBar = new Item("Chocolate Bar", 15, LocalDate.of(2023, 2, 1));
        Item tea = new Item("Tea", 12, LocalDate.of(2022, 8, 1));
        Item coffee = new Item("Coffee", 20);
        Item milk = new Item("Milk", 25);

        store.addItem((chocolateBar));
        store.addItem((tea));
        store.addItem((coffee));
        store.addItem((milk));

        System.out.println(store.getCurrentVolume());
        store.sortByDate(SortOrder.DESC);
        store.displayItems();
    }
}