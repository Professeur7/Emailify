// model/Employee.java
package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final String name;
    private final List<String> receivedMessages = new ArrayList<>();

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receiveMessage(String message) {
        receivedMessages.add(message);
    }

    public List<String> getReceivedMessages() {
        return receivedMessages;
    }
}
