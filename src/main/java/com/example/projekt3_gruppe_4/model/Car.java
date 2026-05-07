package com.example.projekt3_gruppe_4.model;

public class Car {
    private int vehicle_no;
    private String chassis_no;
    private String brand;
    private String model;
    private double purchase_price;
    private String status;

    public Car(){

    }

    public Car(int vehicle_no, String chassis_no, String brand, String model, double purchase_price, String status) {
        this.vehicle_no = vehicle_no;
        this.chassis_no = chassis_no;
        this.brand = brand;
        this.model = model;
        this.purchase_price = purchase_price;
        this.status = status;
    }

    public int getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(int vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getChassis_no() {
        return chassis_no;
    }

    public void setChassis_no(String chassis_no) {
        this.chassis_no = chassis_no;
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

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
