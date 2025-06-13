package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final String name;
    private final String email; // ✅ Ajout de l'attribut email
    private final List<String> receivedMessages = new ArrayList<>();

    // ✅ Nouveau constructeur prenant en compte le nom et l’email
    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void receiveNotification(String message) {
        receivedMessages.add(message);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email; // ✅ Renvoie correctement l’email
    }

    public void receiveMessage(String message) {
        receivedMessages.add(message);
    }

    public List<String> getReceivedMessages() {
        return receivedMessages;
    }
}
