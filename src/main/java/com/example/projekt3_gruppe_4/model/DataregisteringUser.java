package com.example.projekt3_gruppe_4.model;

public class DataregisteringUser extends User {
    DataregisteringUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage() {
        return "dataregistrering/ledige-biler";
    }

    @Override
    public boolean hasAccess(String page) {
        return switch (page) {
            case "dataregistrering/ledige-biler", "dataregistrering/opret-lejeaftale" -> true;
            default -> false;
        };
    }

    @Override
    public String getRoleDisplayName() {
        return "Dataregistrering";
    }
}
