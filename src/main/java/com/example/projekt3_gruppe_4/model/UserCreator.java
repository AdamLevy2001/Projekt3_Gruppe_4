package com.example.projekt3_gruppe_4.model;

public class UserCreator {
    public static User createUser(int id, String username, String password, String role) {
        return switch (role) {
            case "dataregistrering" -> new DataregisteringUser(id, username, password, role);
            case "skadeudbedring" -> new SkadeUdbedringUser(id, username, password, role);
            case "forretningsudvikler" -> new ForretningsudviklerUser(id, username, password, role);
            case "admin" -> new AdminUser(id, username, password, role);
            default -> throw new IllegalArgumentException("Ukendt rolle: " + role);
        };
    }
}
