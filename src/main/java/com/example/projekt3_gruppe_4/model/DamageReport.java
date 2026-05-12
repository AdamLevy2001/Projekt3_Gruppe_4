package com.example.projekt3_gruppe_4.model;

import java.time.LocalDate;

public class DamageReport {
    private int id;
    private int leaseId;
    private LocalDate createdAt;

    public DamageReport(int id, int leaseId, LocalDate createdAt) {
        this.id = id;
        this.leaseId = leaseId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
