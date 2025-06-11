package service;

import model.Employee;
import notification.Notification;

import java.util.*;

public class NotificationService {
    private final Set<Employee> subscribers = new HashSet<>();
    private final List<Notification> channels;

    public NotificationService(List<Notification> notifications) {
        this.channels = notifications;
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
            if (!subscriber.equals(sender)) {
                for (Notification channel : channels) {
                    // ✅ Correction : passer les noms (String), pas les objets
                    channel.send(message, sender.getName(), subscriber.getName());
                }

                // ✅ Sauvegarder la notification dans les messages reçus
                subscriber.receiveNotification(
                        String.format("Message de %s : %s", sender.getName(), message)
                );
            }
        }

        System.out.println("Message envoyé à tous les abonnés (sauf l'expéditeur).");
    }

    public Set<Employee> getSubscribers() {
        return subscribers;
    }
}
