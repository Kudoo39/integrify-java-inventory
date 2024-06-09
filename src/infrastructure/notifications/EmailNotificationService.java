package infrastructure.notifications;

import domain.abstractions.INotificationService;
import domain.entities.Item;

public class EmailNotificationService implements INotificationService {
    @Override
    public void sendNotificationOnSuccess(Item item, String message) {
        System.out.println("Hello, a new item titled " + item.getName() + " has been successfully " + message + " the inventory. If you have any queries or feedback, please contact our support team at support@inventory.com.");
    }

    @Override
    public void sendNotificationOnFailure(Item item, String message) {
        System.out.println("We encountered an issue " + message + " " + item.getName() + ". Please review the input data. For more help, visit our FAQ at inventory.com/faq.");
    }
}
