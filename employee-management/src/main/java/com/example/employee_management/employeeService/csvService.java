package com.example.employee_management.employeeService;

import com.example.employee_management.model.Person;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class csvService {
    public List<Person> readEmployeesFromCsv(String filePath){
        List<Person> employees = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))){
            String[] line;
            while((line = reader.readNext()) != null){
                employees.add(new Person(line[0], line[1], line[2], line[3], line[4], line[5]));
            }

        }catch (Exception e){
            System.out.println("Error related to csv file!");
        }
        return employees;
    }
}
