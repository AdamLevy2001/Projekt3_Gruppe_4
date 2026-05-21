package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class Lease {
    private int id;
    private int carVehicleNo;
    private int customerId;
    private int deliveryLocationId;
    private double downPayment;
    private double monthlyPayment;
    private int kmPerMonth;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public Lease() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarVehicleNo() {
        return carVehicleNo;
    }

    public void setCarVehicleNo(int carVehicleNo) {
        this.carVehicleNo = carVehicleNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(int deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getKmPerMonth() {
        return kmPerMonth;
    }

    public void setKmPerMonth(int kmPerMonth) {
        this.kmPerMonth = kmPerMonth;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
