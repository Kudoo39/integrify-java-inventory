package infrastructure.notifications;

import domain.abstractions.INotificationService;
import domain.entities.Item;

public class SMSNotificationService implements INotificationService {
    @Override
    public void sendNotificationOnSuccess(Item item, String message) {
        System.out.println(item.getName() + " " + message + " inventory. Thank you!");
    }

    @Override
    public void sendNotificationOnFailure(Item item, String message) {
        System.out.println("Error " + message + " item " + item.getName() + ". Please email support@inventory.com.");
    }
}
