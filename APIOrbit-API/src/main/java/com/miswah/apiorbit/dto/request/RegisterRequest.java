package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.enums.Roles;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Roles role;

    // Getters, setters, constructors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
