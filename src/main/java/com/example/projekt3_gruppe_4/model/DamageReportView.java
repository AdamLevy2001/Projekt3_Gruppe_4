package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReportView {
    private int id;
    private int vehicleNo;
    private LocalDate createdAt;
    private String brand;
    private String model;
    private String firstName;
    private String lastName;

    public DamageReportView(int id, int vehicleNo, LocalDate createdAt, String brand, String model, String firstName, String lastName) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.createdAt = createdAt;
        this.brand = brand;
        this.model = model;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(int vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}