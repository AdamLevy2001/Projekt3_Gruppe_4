package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReport {
    private int id;
    private int lease_id;
    private LocalDate created_at;

    public DamageReport(int id, int lease_id, LocalDate created_at) {
        this.id = id;
        this.lease_id = lease_id;
        this.created_at = created_at;
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
}
