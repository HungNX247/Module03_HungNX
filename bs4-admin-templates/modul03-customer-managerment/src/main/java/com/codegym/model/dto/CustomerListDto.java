package com.codegym.model.dto;

public class CustomerListDto {
    private Integer id;
    private String name;
    private String position;
    private String office;

    public CustomerListDto() {
    }

    public CustomerListDto(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    public CustomerListDto(Integer id, String name, String position, String office) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.office = office;
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
}
