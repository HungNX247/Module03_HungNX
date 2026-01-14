package com.codegym.model.dto;

public class UserDto {
    private int id;
    private String username;
    private String roles;

    public UserDto() {
    }

    public UserDto(String username, String roles) {
        this.username = username;
        this.roles = roles;
    }

    public UserDto(int id, String username, String roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
