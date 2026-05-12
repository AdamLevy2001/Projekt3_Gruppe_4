package com.example.projekt3_gruppe_4.model;

public class SkadeUdbedringUser extends User {
    SkadeUdbedringUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage() {
        return "skade-udbedring/tilbageleverede-biler";
    }

    @Override
    public boolean hasAccess(String page) {
        return switch (page) {
            case "skade-udbedring/tilbageleverede-biler", "skade-udbedring/skadesrapport", "skade-udbedring/skadesrapporter" -> true;
            default -> false;
        };
    }

    @Override
    public String getRoleDisplayName() {
        return "Skade og udbedring";
    }
}
