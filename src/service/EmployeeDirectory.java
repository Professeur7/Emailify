// service/EmployeeDirectory.java
package service;

import model.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDirectory {
    private final Map<String, Employee> employees = new HashMap<>();

    public void addEmployee(String name) {
        employees.putIfAbsent(name, new Employee(name));
    }

    public Employee getEmployee(String name) {
        return employees.get(name);
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Aucun employé enregistré.");
        } else {
            employees.keySet().forEach(System.out::println);
        }
    }
}
