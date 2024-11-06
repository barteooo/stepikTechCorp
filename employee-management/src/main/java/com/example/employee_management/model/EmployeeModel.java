package com.example.employee_management.model;

import com.example.employee_management.service.csvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeModel {
    private final List<Person> employees = new ArrayList<>();
    private final csvService csvService;

    @Autowired
    public EmployeeModel(csvService csvService) {
        this.csvService = csvService;
        this.employees.addAll(csvService.readEmployeesFromCsv("src/main/resources/MOCK_DATA.csv"));
    }

    public List<Person> findAll() {
        return new ArrayList<>(employees);
    }

    public Optional<Person> findByEmail(String email) {
        return employees.stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void addEmployee(Person person) {
        employees.add(person);
    }

    public boolean deleteEmployee(String email) {
        return employees.removeIf(e -> e.getEmail().equalsIgnoreCase(email));
    }

    public Optional<Person> updateEmployee(String email, Person updatedPerson) {
        Optional<Person> existingEmployeeOpt = findByEmail(email);

        if (existingEmployeeOpt.isPresent()) {
            Person existingEmployee = existingEmployeeOpt.get();

            if (updatedPerson.getFirstName() != null) {
                existingEmployee.setFirstName(updatedPerson.getFirstName());
            }
            if (updatedPerson.getLastName() != null) {
                existingEmployee.setLastName(updatedPerson.getLastName());
            }
            if (updatedPerson.getCountry() != null) {
                existingEmployee.setCompany(updatedPerson.getCountry());
            }
            if (updatedPerson.getSalary() != null) {
                existingEmployee.setSalary(updatedPerson.getSalary());
            }
            if (updatedPerson.getCurrency() != null) {
                existingEmployee.setCurrency(updatedPerson.getCurrency());
            }

            return Optional.of(existingEmployee);
        }

        return Optional.empty();
    }

    public List<Person> filterEmployeesByCompany(String country){
        List<Person> filteredEmployees = employees.stream()
                .filter(e -> e.getCompany().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return filteredEmployees;
    }

    public List<Person> sortEmployeesByLastName() {
        employees.sort(Comparator.comparing(Person::getLastName));
        System.out.println("\nEmployees sorted by last name:");
        employees.forEach(person -> {System.out.println(person.toString());});
        return employees;
    }
}
