package com.example.employee_management.employeeService;

import com.example.employee_management.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementService {
    private final List<Person> employeeList;


    public EmployeeManagementService() {
        csvService csvService = new csvService();
        this.employeeList = csvService.readEmployeesFromCsv("src/main/resources/MOCK_DATA.csv");

    }

    public List<Person> getAllEmployees() {
        return employeeList;
    }

    public Person getEmployeeByEmail(String email) {
        return employeeList.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
    public void addEmployee(Person person) {
        employeeList.add(person);
    }
    public Optional<Person> updateEmployee(String email, Person updatedPerson) {
        Optional<Person> existingEmployeeOpt = Optional.ofNullable(getEmployeeByEmail(email));
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

    public boolean deleteEmployee(String email) {
        Optional<Person> employeeOpt = Optional.ofNullable(getEmployeeByEmail(email));
        if (employeeOpt.isPresent()) {
            employeeList.remove(employeeOpt.get());
            return true;
        }
        return false;
    }


}
