package domain.abstractions;

import domain.entities.Item;

public interface INotificationService {
    void sendNotificationOnSuccess(Item item, String message);
    void sendNotificationOnFailure(Item item, String message);
}
