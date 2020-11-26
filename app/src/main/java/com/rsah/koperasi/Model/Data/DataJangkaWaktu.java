package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataJangkaWaktu {


    @SerializedName("IDTenor")
    private String IDTenor;

    @SerializedName("Tenor")
    private String Tenor;

    @SerializedName("Bunga")
    private String Bunga;

    public String getIDTenor() {
        return IDTenor;
    }

    public void setIDTenor(String IDTenor) {
        this.IDTenor = IDTenor;
    }

    public String getTenor() {
        return Tenor;
    }

    public void setTenor(String tenor) {
        Tenor = tenor;
    }

    public String getBunga() {
        return Bunga;
    }

    public void setBunga(String bunga) {
        Bunga = bunga;
    }


}
