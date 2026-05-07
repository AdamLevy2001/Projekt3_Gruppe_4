package com.example.projekt3_gruppe_4.model;

public class ForretningsudviklerUser extends User{
    ForretningsudviklerUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage(){
        return "dashboard";
    }

    @Override
    public boolean hasAccess(String page){
        return switch (page){
            case "seUdlejedeBiler", "seSkadesrapporter", "seLedigeBiler", "dashboard" -> true;
            default -> false;
        };
    }
}
