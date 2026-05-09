package com.example.projekt3_gruppe_4.model;

public class Car {
    private int vehicleNo;
    private String chassisNo;
    private String brand;
    private String model;
    private double purchasePrice;
    private String status;

    public Car() {

    }

    public Car(int vehicleNo, String chassisNo, String brand, String model, double purchasePrice, String status) {
        this.vehicleNo = vehicleNo;
        this.chassisNo = chassisNo;
        this.brand = brand;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.status = status;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(int vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
