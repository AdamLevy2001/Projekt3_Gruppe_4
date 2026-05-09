package com.example.projekt3_gruppe_4.model;

public class Damage {

    int id;

    int damageReportId;

    String description;

    double price;

    public Damage(int id, int damageReportId, String description, double price) {
        this.id = id;
        this.damageReportId = damageReportId;
        this.description = description;
        this.price = price;
    }

    public Damage(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public Damage(int damageReportId, String description, double price) {
        this.damageReportId = damageReportId;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamageReportId() {
        return damageReportId;
    }

    public void setDamageReportId(int damageReportId) {
        this.damageReportId = damageReportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


