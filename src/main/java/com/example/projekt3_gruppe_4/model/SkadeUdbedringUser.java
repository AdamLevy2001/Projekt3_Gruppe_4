package com.example.projekt3_gruppe_4.model;

public class SkadeUdbedringUser extends User{
    SkadeUdbedringUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage(){
        return "seTilbageleveredeBiler";
    }

    @Override
    public boolean hasAccess(String page){
        return switch (page){
            case "seTilbageleveredeBiler", "opretSkadesrapport", "seSkadesrapporter" -> true;
            default -> false;
        };
    }
}
