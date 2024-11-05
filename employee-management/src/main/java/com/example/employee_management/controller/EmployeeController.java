package com.example.employee_management.controller;

import com.example.employee_management.employeeService.EmployeeManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.employee_management.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeManagementService employeeManagementService;

    public EmployeeController(){
        this.employeeManagementService = new EmployeeManagementService();
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllEmployees() {
        List<Person> employees = employeeManagementService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable String email) {
        Person employee = employeeManagementService.getEmployeeByEmail(email);

        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Employee with email " + email + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addEmployee(@RequestBody Person person) {
        if (person.getFirstName() == null || person.getFirstName().isEmpty() ||
                person.getLastName() == null || person.getLastName().isEmpty() ||
                person.getEmail() == null || person.getEmail().isEmpty() ||
                person.getCountry() == null || person.getCountry().isEmpty() ||
                person.getSalary() == null || person.getSalary().isEmpty() ||
                person.getCurrency() == null || person.getCurrency().isEmpty()) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "All fields are required and must be non-empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        employeeManagementService.addEmployee(person);
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("message", "Employee added successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Map<String, String>> updateEmployee(@PathVariable String email, @RequestBody Person updatedPerson) {
        Optional<Person> existingEmployee = employeeManagementService.updateEmployee(email, updatedPerson);

        if (existingEmployee.isPresent()) {
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "Employee updated successfully.");
            return ResponseEntity.ok(successResponse);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Employee not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String email) {
        boolean isDeleted = employeeManagementService.deleteEmployee(email);

        if (isDeleted) {
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "Employee deleted successfully.");
            return ResponseEntity.ok(successResponse);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Employee not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}

