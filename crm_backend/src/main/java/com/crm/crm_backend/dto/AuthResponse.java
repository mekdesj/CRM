package com.crm.crm_backend.dto;

public class AuthResponse {
    private String token;

    // Constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter only — no setter needed, token is final after creation
    public String getToken() {
        return token;
    }
}
