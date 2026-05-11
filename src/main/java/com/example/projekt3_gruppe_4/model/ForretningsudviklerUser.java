package com.example.projekt3_gruppe_4.model;

public class ForretningsudviklerUser extends User {
    ForretningsudviklerUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage() {
        return "forretningsudvikler/dashboard";
    }

    @Override
    public boolean hasAccess(String page) {
        return switch (page) {
            case "forretningsudvikler/udlejede-biler", "forretningsudvikler/skadesrapporter", "forretningsudvikler/skadesrapport", "forretningsudvikler/dashboard" -> true;
            default -> false;
        };
    }

    @Override
    public String getRoleDisplayName() {
        return "Forretningsudvikler";
    }
}
