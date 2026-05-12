package com.example.projekt3_gruppe_4.model;

public class AdminUser extends User {
    AdminUser(int id, String username, String password, String role) {
        super(id, username, password, role);
    }

    @Override
    public String getDefaultPage() {
        return "admin/opret-bruger";
    }

    @Override
    public boolean hasAccess(String page) {
        return true;
    }

    @Override
    public String getRoleDisplayName() {
        return "Administrator";
    }
}
