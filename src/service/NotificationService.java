package service;

import jakarta.mail.MessagingException;
import model.Employee;
import notification.Notification;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class NotificationService {
    private final EmployeeDirectory directory;
    private final Set<Employee> subscribers = new HashSet<>();
    private final List<Notification> channels;

    public NotificationService(EmployeeDirectory directory, List<Notification> channels) {
        this.directory = directory;
        this.channels = channels;
    }

    public NotificationService(List<Notification> channels) {
        this(null, channels);
    }

    public void subscribe(Employee e) { subscribers.add(e); }
    public void unsubscribe(Employee e) { subscribers.remove(e); }
    public boolean isSubscribed(Employee e) { return subscribers.contains(e); }
    public Set<Employee> getSubscribers() { return new HashSet<>(subscribers); }

    public void sendMessage(Employee sender, String message) throws MessagingException {
        for (Employee receiver : subscribers) {
            if (!receiver.equals(sender)) {
                for (Notification channel : channels) {
                    channel.send(message, sender, receiver);
                }
            }
        }
        System.out.println("✅ Message envoyé à tous les abonnés (sauf expéditeur).");
    }
}
