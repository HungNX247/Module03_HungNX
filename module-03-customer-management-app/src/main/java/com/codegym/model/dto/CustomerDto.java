package com.codegym.model.dto;

import java.util.Date;

public class CustomerDto {
    private Integer id;
    private String name;
    private String position;
    private String office;
    private Integer age;
    private Date startDate;
    private Double salary;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String name, String position, String office, Integer age, Date startDate, Double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.startDate = startDate;
        this.salary = salary;
    }

    public CustomerDto(String name, String position, String office, Integer age, Date startDate, Double salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.startDate = startDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
