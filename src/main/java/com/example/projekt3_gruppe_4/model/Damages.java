package com.example.projekt3_gruppe_4.model;

public class Damages {

    int id;

    int damageReportID;

    String description;

    double price;

    public Damages(int id, int damageReportID, String description, double price) {
        this.id = id;
        this.damageReportID = damageReportID;
        this.description = description;
        this.price = price;
    }

    public Damages(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamageReportID() {
        return damageReportID;
    }

    public void setDamageReportID(int damageReportID) {
        this.damageReportID = damageReportID;
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


