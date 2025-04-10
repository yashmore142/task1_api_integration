package com.example.banking_app_java.ui.loginscreen.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;


    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

}
