package com.miswah.apiorbit.dto.request;

public class AuthenticationRequest {
    private String email;
    private String password;

    // Getters, setters, constructors
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
