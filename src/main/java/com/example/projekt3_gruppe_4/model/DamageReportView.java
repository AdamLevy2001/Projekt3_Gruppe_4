package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReportView {
    private int id;
    private int vehicle_no;
    private LocalDate created_at;
    private String brand;
    private String model;
    private String firstName;
    private String lastName;

    public DamageReportView(int id, int vehicle_no, LocalDate created_at, String brand, String model, String firstName, String lastName) {
        this.id = id;
        this.vehicle_no = vehicle_no;
        this.created_at = created_at;
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

    public int getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(int vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
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