package com.example.projekt3_gruppe_4.model;

public class UserCreator {
    public static User createUser(int id, String username, String password, String role){
        return switch (role){
            case "dataRegistering" -> new DataregisteringUser(id, username, password, role);
            case "skadeUdbedring" -> new SkadeUdbedringUser(id, username, password, role);
            case "forretningsudvikler" -> new ForretningsudviklerUser(id, username, password, role);
            default -> throw new IllegalArgumentException("Ukendt rolle: " + role);
        };
    }
}
