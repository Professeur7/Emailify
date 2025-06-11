// notification/EmailNotification.java
package notification;

public class EmailNotification implements Notification {
    @Override
    public void send(String message, String sender, String receiver) {
        System.out.println("[Email] À : " + receiver + " | De : " + sender + " | Sujet : Notification | Message : " + message);
    }
}
