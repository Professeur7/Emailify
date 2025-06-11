// service/NotificationService.java
package service;

import model.Employee;
import notification.Notification;

import java.util.*;

public class NotificationService {
    private final Set<Employee> subscribers = new HashSet<>();
    private final List<Notification> channels = new ArrayList<>();

    public NotificationService(List<Notification> notifications) {
        this.channels.addAll(notifications);
    }

    public void subscribe(Employee employee) {
        subscribers.add(employee);
    }

    public void unsubscribe(Employee employee) {
        subscribers.remove(employee);
    }

    public boolean isSubscribed(Employee employee) {
        return subscribers.contains(employee);
    }

    public void sendMessage(Employee sender, String message) {
        for (Employee subscriber : subscribers) {
            if (!subscriber.getName().equals(sender.getName())) {
                for (Notification notification : channels) {
                    notification.send(message, sender.getName(), subscriber.getName());
                }
                subscriber.receiveMessage("De " + sender.getName() + " : " + message);
            }
        }
    }

    public Set<Employee> getSubscribers() {
        return subscribers;
    }
}
