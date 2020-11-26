package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataNominal {


    @SerializedName("id")
    private String id;

    @SerializedName("nominal")
    private String nominal;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
































}
