package com.example.employee_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.util.Strings;

public class Person {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("country")
    private String country;

    @JsonProperty("salary")
    private String salary;

    @JsonProperty("currency")
    private String currency;

    public Person() {

    }

    public Person(String firstName, String lastName, String email, String salary, String currency, String country){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.salary = salary;
        this.currency = currency;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getCompany() {
        return country;
    }
    public void setCompany(String company){
        this.country = company;
    }
    public String getCurrency() {
        return currency;
    }

    public String getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
