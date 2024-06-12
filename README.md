# Inventory Management

In this assignment, you will create a simple inventory management system which allows users to add, remove, and view items in the inventory.

## Requirements

## Level 1

1. Create class `Item`, which has name (readonly), quantity, and created date, which are private. Amount of each item cannot be negative. Provide the following features:

- Constructor to take parameters of name, quantity, and created date (optional, if not set, it will be current date).

2. Create class `Store` with the following properties and methods:

- A collection to store items, which is private. Initially, this will be an empty collection.
- Methods to add/delete one item to the collection. Do not allow adding items with the same name to the store.
- Method `getCurrentVolume` to compute the total amount of items in the store.
- Method `findItemByName` to find an item by name.
- Method `sortByNameAsc` to get the sorted collection by name in ascending order.

```java
// items example - You do not need to follow exactly the same
Item waterBottle = new Item("Water Bottle", 10, LocalDate.of(2023, 1, 1));
Item chocolateBar = new Item("Chocolate Bar", 15, LocalDate.of(2023, 2, 1));
Item notebook = new Item("Notebook", 5, LocalDate.of(2023, 3, 1));
Item pen = new Item("Pen", 20, LocalDate.of(2023, 4, 1));
Item tissuePack = new Item("Tissue Pack", 30, LocalDate.of(2023, 5, 1));
Item chipsBag = new Item("Chips Bag", 25, LocalDate.of(2023, 6, 1));
Item sodaCan = new Item("Soda Can", 8, LocalDate.of(2023, 7, 1));
Item soap = new Item("Soap", 12, LocalDate.of(2023, 8, 1));
Item shampoo = new Item("Shampoo", 40, LocalDate.of(2023, 9, 1));
Item toothbrush = new Item("Toothbrush", 50, LocalDate.of(2023, 10, 1));
Item coffee = new Item("Coffee", 20);
Item sandwich = new Item("Sandwich", 15);
Item batteries = new Item("Batteries", 10);
Item umbrella = new Item("Umbrella", 5);
Item sunscreen = new Item("Sunscreen", 8);
```

## Level 2

Class `Store` should have the following features:

- Maximum capacity, which is the total amount of items allowed in the store, and the constructor should also take an integer value as the maximum capacity of the inventory.
- Modify the add method to not overload the capacity.

## Level 3

Class `Store` should have extra features

- Method `sortByDate` to get the sorted collection by date dynamically (asc or desc).
  ```java
   // method invocation example - You do not need to follow exactly the same
  Store store = new Store(300);
  // ... add all items to the store
  List<Item> collectionSortedByDate = store.sortByDate(SortOrder.DESC);
  // print all items
  ...
  ```
- Method `groupByDate` to return 2 groups `New Arrival` and `Old` items. `New Arrival` items are those created within the last three months. For example, if the current month is October, then items created in August, September, and October are categorized as `New Arrival`. Items created before August are categorized as `Old`.

  ```java
  // method invocation example - You do not need to follow exactly the same
  Store store = new Store(300);
  // ... add all items to the store
  Map<String, List<Item>> groupByDate = store.groupByDate();
  for (Map.Entry<String, List<Item>> group : groupByDate.entrySet()) {
      System.out.println(group.getKey() + " Items:");
      for (Item item : group.getValue()) {
          System.out.println(" - " + item.getName() + ", Created: " + item.getCreatedDate().toString());
      }
  }

  //Expected outcome
  New Arrival Items:
   - Coffee, Created: [current date]
   - Sandwich, Created: [current date]
   - Batteries, Created: [current date]
   - Umbrella, Created: [current date]
   - Sunscreen, Created: [current date]

  Old Items:
   - Water Bottle, Created: 2023-01-01
   - Chocolate Bar, Created: 2023-02-01
   - Notebook, Created: 2023-03-01
   - Pen, Created: 2023-04-01
   - Tissue Pack, Created: 2023-05-01
   - Chips Bag, Created: 2023-06-01
   - Soda Can, Created: 2023-07-01
   - Soap, Created: 2023-08-01
   - Shampoo, Created: 2023-09-01
   - Toothbrush, Created: 2023-10-01
  ```

## Level 4

1. Create an `INotificationService` interface with a method `sendNotificationOnSuccess` and `sendNotificationOnFailure`.
2. Create two different notification service implementations: `EmailNotificationService` and `SMSNotificationService`.
- EmailNotificationService implementation:
    - `sendNotificationOnSuccess`: Sends a comprehensive email, including action details, a summary of the item, user feedback instructions, and a support contact. For example, "Hello, a new item titled 'XYZ' has been successfully added to the inventory. If you have any queries or feedback, please contact our support team at support@inventory.com."
    - `sendNotificationOnFailure`: Provides a detailed error report, troubleshooting steps, and a link to an FAQ or help page. E.g., "We encountered an issue adding 'ABC'. Please review the input data. For more help, visit our FAQ at inventory.com/faq."
- SMSNotificationService Implementation:
    - `sendNotificationOnSuccess`: Sends a brief SMS with action confirmation and a short status update. E.g., "Item 'XYZ' added to inventory. Thank you!"
    - `sendNotificationOnFailure`: Provides a short error notice and a suggestion to contact support via a different channel. E.g., "Error adding item 'ABC'. Please email support@inventory.com."
3. Inject `INotificationService` into the `Store` class. Send notifications on adding or deleting an item.
4. We will have 2 inventories, using different implementation of notification service. Use all the features above in `Program.java`.

   ```java
   public class Program {
       public static void main(String[] args) {
           INotificationService emailService = new EmailNotificationService();
           INotificationService smsService = new SMSNotificationService();

           Store storeWithEmail = new Store(emailService);
           Store storeWithSMS = new Store(smsService);

           // Demonstrate adding/removing items in each store and observe notifications
       }
   }
   ```
