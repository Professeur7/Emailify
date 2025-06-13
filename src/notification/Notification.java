// notification/Notification.java
package notification;

import jakarta.mail.MessagingException;
import model.Employee;

public interface Notification {
    void send(String message, String sender, String receiver) throws MessagingException;

    void send(String message, Employee sender, Employee receiver);
}
