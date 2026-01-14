package com.codegym.model.entity;

import java.util.Date;

public class Customer {
    private Integer id;
    private String name;
    private String position;
    private String office;
    private Integer age;
    private Date start_date;
    private Double salary;

    public Customer() {
    }

    public Customer(String name, String position, String office, Integer age, Date start_date, Double salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.start_date = start_date;
        this.salary = salary;
    }

    public Customer(Integer id, String name, String position, String office, Integer age, Date start_date, Double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.start_date = start_date;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
