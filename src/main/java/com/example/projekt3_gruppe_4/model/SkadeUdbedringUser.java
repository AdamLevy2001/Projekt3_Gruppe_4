package com.example.projekt3_gruppe_4.model;

public class SkadeUdbedringUser extends User {
    SkadeUdbedringUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage() {
        return "tilbageleverede-biler";
    }

    @Override
    public boolean hasAccess(String page) {
        return switch (page) {
            case "tilbageleverede-biler", "createDamage", "showDamageReport" -> true;
            default -> false;
        };
    }
}
