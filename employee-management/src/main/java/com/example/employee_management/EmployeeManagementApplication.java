package com.example.employee_management;

import com.example.employee_management.model.EmployeeModel;
import com.example.employee_management.model.Person;
import com.example.employee_management.service.csvService;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Person president = context.getBean("president", Person.class);
		Person vicePresident = context.getBean("vicePresident", Person.class);
		Person secretary = context.getBean("secretary", Person.class);

		System.out.println("Employees from XML:");
		System.out.println(president);
		System.out.println(vicePresident);
		System.out.println(secretary);

		csvService csvService = new csvService();
		List<Person> employees = csvService.readEmployeesFromCsv("src/main/resources/MOCK_DATA.csv");


	}


 }