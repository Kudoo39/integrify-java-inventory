package domain.entities;

import java.time.LocalDate;

public class Item {
    private String name;
    private int quantity;
    private LocalDate createdDate;

    public Item(String name, int quantity, LocalDate createdDate) {
        this.name = name;
        if (quantity < 0) {
            throw new IllegalArgumentException("Only positive numbers!");
        }
        this.quantity = quantity;
        this.createdDate = createdDate;
    }

    public Item(String name, int quantity) {
        this.name = name;
        if (quantity < 0) {
            throw new IllegalArgumentException("Only positive numbers!");
        }
        this.quantity = quantity;
        this.createdDate = LocalDate.now();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return createdDate;
    }
}
