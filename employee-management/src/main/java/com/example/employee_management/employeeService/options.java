package com.example.employee_management.employeeService;

import com.example.employee_management.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class options {

    public static List<Person> filterEmployeesByCompany(List<Person> employees, String country){
        List<Person> filteredEmployees = employees.stream()
                .filter(e -> e.getCompany().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return filteredEmployees;
    }
    public static List<Person> sortEmployeesByLastName(List<Person> employees) {
        employees.sort(Comparator.comparing(Person::getLastName));
        System.out.println("\nEmployees sorted by last name:");
        employees.forEach(person -> {System.out.println(person.toString());});
        return employees;
    }
}
