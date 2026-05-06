package com.example.projekt3_gruppe_4.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Lease {

private int id;
private int carVehicle_no;
private int customer_id;
private int deliveryLocation_id;
private double down_payment;
private double monthly_payment;
private int km_per_month;
private LocalDate start_date;
private LocalDate end_date;
private String status;

public Lease(int id, int carVehicle_no, int customer_id, int deliveryLocation_id,
             double down_payment, double monthly_payment, int km_per_month,
             LocalDate start_date, LocalDate end_date, String status) {
    this.id=id;
    this.carVehicle_no=carVehicle_no;
    this.customer_id=customer_id;
    this.deliveryLocation_id=deliveryLocation_id;
    this.down_payment=down_payment;
    this.monthly_payment=monthly_payment;
    this.km_per_month=km_per_month;
    this.start_date=start_date;
    this.end_date=end_date;
    this.status=status;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarVehicle_no() {
        return carVehicle_no;
    }

    public void setCarVehicle_no(int carVehicle_no) {
        this.carVehicle_no = carVehicle_no;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getDeliveryLocation_id() {
        return deliveryLocation_id;
    }

    public void setDeliveryLocation_id(int deliveryLocation_id) {
        this.deliveryLocation_id = deliveryLocation_id;
    }

    public double getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(double down_payment) {
        this.down_payment = down_payment;
    }

    public double getMonthly_payment() {
        return monthly_payment;
    }

    public void setMonthly_payment(double monthly_payment) {
        this.monthly_payment = monthly_payment;
    }

    public int getKm_per_month() {
        return km_per_month;
    }

    public void setKm_per_month(int km_per_month) {
        this.km_per_month = km_per_month;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
