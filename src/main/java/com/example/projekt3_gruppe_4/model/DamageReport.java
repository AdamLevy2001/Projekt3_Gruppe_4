package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReport {

    private int id;

    private int lease_id;

    private LocalDate created_at;

    private String brand;


    private String model;

    private String customerName;

    public DamageReport(int id, int lease_id, LocalDate created_at, String brand, String model, String customerName) {
        this.id = id;
        this.lease_id = lease_id;
        this.created_at = created_at;
        this.brand=brand;
        this.model=model;
        this.customerName=customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLease_id() {
        return lease_id;
    }

    public void setLease_id(int lease_id) {
        this.lease_id = lease_id;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
