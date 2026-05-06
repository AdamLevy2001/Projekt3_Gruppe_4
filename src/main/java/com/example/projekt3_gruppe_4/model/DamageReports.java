package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReports {

    private int id;

    private int LeasesID;

    private LocalDate date;

    public DamageReports(int id, int leasesID, LocalDate date) {
        this.id = id;
        LeasesID = leasesID;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeasesID() {
        return LeasesID;
    }

    public void setLeasesID(int leasesID) {
        LeasesID = leasesID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
