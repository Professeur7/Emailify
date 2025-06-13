package notification;

import model.Employee;

public abstract class ConsoleNotification implements Notification {
    @Override
    public void send(String message, Employee sender, Employee receiver) {
        System.out.println("[Console] À : " + receiver.getName() +
                " | De : " + sender.getName() +
                " | Message : " + message);
        receiver.receiveNotification("[Console] De " + sender.getName() + " : " + message);
    }
}
