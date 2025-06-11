// notification/ConsoleNotification.java
package notification;

public class ConsoleNotification implements Notification {
    @Override
    public void send(String message, String sender, String receiver) {
        System.out.println("[Console] À : " + receiver + " | De : " + sender + " | Message : " + message);
    }
}
