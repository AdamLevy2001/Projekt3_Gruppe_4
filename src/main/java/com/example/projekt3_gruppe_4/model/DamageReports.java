package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReports {

    private int id;

    private int leasesID;

    private LocalDate created_at;

    public DamageReports(int id, int leasesID, LocalDate created_at) {
        this.id = id;
        this.leasesID = leasesID;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeasesID() {
        return leasesID;
    }

    public void setLeasesID(int leasesID) {
        this.leasesID = leasesID;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
