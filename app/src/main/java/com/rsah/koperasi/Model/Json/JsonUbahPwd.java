package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonUbahPwd {

    @SerializedName("email")
    private String email;

    @SerializedName("pass_old")
    private String pass_old;

    @SerializedName("pass_baru")
    private String pass_baru;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass_old() {
        return pass_old;
    }

    public void setPass_old(String pass_old) {
        this.pass_old = pass_old;
    }

    public String getPass_baru() {
        return pass_baru;
    }

    public void setPass_baru(String pass_baru) {
        this.pass_baru = pass_baru;
    }






}
