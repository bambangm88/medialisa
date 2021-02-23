package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataCaraPembayaran {



    @SerializedName("ID")
    private String ID;

    @SerializedName("no_rekening")
    private String no_rekening;

    @SerializedName("nama_rekening")
    private String nama_rekening;

    @SerializedName("bank_rekening")
    private String bank_rekening;

    @SerializedName("wa_admin")
    private String wa_admin;

    public String getWa_admin() {
        return wa_admin;
    }

    public void setWa_admin(String wa_admin) {
        this.wa_admin = wa_admin;
    }




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(String no_rekening) {
        this.no_rekening = no_rekening;
    }

    public String getNama_rekening() {
        return nama_rekening;
    }

    public void setNama_rekening(String nama_rekening) {
        this.nama_rekening = nama_rekening;
    }

    public String getBank_rekening() {
        return bank_rekening;
    }

    public void setBank_rekening(String bank_rekening) {
        this.bank_rekening = bank_rekening;
    }


































}
