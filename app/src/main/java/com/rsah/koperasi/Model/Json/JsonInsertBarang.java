package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonInsertBarang {


    @SerializedName("IDMember")
    private String IDMember;

    @SerializedName("IDBarang")
    private String IDBarang;


    public String getIDMember() {
        return IDMember;
    }

    public void setIDMember(String IDMember) {
        this.IDMember = IDMember;
    }

    public String getIDBarang() {
        return IDBarang;
    }

    public void setIDBarang(String IDBarang) {
        this.IDBarang = IDBarang;
    }





}
