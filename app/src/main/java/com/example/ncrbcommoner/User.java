package com.example.ncrbcommoner;

import androidx.annotation.Keep;

public class User {
    @Keep

    public String username , phone , email, password;
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public User(String username, String phone, String email, String password) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

}
