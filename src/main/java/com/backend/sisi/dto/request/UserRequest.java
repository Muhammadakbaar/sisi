package com.backend.sisi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotNull(message = "email tidak boleh kosong")
    @Email
    private String email;

    @NotNull(message = "password tidak boleh kosong")
    @Size(min = 6)
    private String password;

    @NotNull(message = "role tidak boleh kosong")
    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
