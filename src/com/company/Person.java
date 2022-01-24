package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private String name;
    private String email;
    private String phonenumber;
    private LocalDate dateOfBirth;

    public Person(String name, String email, String phonenumber, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String name, String email, String phonenumber, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getAge(){
        LocalDate today = LocalDate.now();
        Period period = Period.between(this.dateOfBirth, today);
        return period.getYears();
    }
}
