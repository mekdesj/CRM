package com.crm.crm_backend.dto;

public class AuthRequest {
    private String username;
    private String password;

    // Constructor
    public AuthRequest() {}

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
