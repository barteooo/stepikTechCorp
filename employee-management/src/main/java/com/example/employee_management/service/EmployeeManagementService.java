package com.example.employee_management.service;

import com.example.employee_management.model.EmployeeModel;
import com.example.employee_management.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementService {

    private final EmployeeModel employeeModel;

    @Autowired
    public EmployeeManagementService(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public List<Person> getAllEmployees() {
        return employeeModel.findAll();
    }

    public Person getEmployeeByEmail(String email) {
        return employeeModel.findByEmail(email).orElse(null);
    }

    public void addEmployee(Person person) {
        employeeModel.addEmployee(person);
    }

    public Optional<Person> updateEmployee(String email, Person updatedPerson) {
        return employeeModel.updateEmployee(email, updatedPerson);
    }

    public boolean deleteEmployee(String email) {
        return employeeModel.deleteEmployee(email);
    }
}
