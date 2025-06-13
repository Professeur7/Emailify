package service;

import model.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDirectory {
    private final Map<String, Employee> employees = new HashMap<>();

    public void addEmployee(String name, String email) {
        employees.put(name.toLowerCase(), new Employee(name, email));
    }

    public Employee getEmployee(String name) {
        return employees.get(name.toLowerCase());
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Aucun employé enregistré.");
            return;
        }
        employees.values().forEach(e ->
                System.out.println("- " + e.getName() + " | " + e.getEmail())
        );
    }
}
