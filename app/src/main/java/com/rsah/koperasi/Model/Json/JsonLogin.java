package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonLogin {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email= username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
