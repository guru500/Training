package com.copious.training.model;

import java.beans.Transient;
import java.time.LocalDate;

public class Employee {

    private int id;
    private String name;
    private long salary;
    private int age;
    private LocalDate dob;
    private String gender;
    private String userName;
    private String password;

    public Employee() {
        super();
    }

    public Employee(int id, String name, long salary, int age, LocalDate dob, String gender) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
