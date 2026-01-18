package com.clinicbooking.model.entity;

public class Doctor {
    private int id;
    private String fullName;
    private int specialtyId;
    private String phone;
    private int price;

    public Doctor() {
    }

    public Doctor(int id, String fullName, int specialtyId, String phone, int price) {
        this.id = id;
        this.fullName = fullName;
        this.specialtyId = specialtyId;
        this.phone = phone;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
