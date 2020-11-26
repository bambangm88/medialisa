package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataPinjaman {


    @SerializedName("id")
    private String id;

    @SerializedName("jenisPinjaman")
    private String jenisPinjaman;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenisPinjaman() {
        return jenisPinjaman;
    }

    public void setJenisPinjaman(String jenisPinjaman) {
        this.jenisPinjaman = jenisPinjaman;
    }































}
