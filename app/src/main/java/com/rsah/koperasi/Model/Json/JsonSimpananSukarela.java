package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonSimpananSukarela {

    @SerializedName("memberID")
    private String memberID;

    @SerializedName("tgl_trf")
    private String tgl_trf;

    @SerializedName("bukti_trf")
    private String bukti_trf;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("CompanyCode")
    private String CompanyCode;

    public String getTgl_trf() {
        return tgl_trf;
    }

    public void setTgl_trf(String tgl_trf) {
        this.tgl_trf = tgl_trf;
    }

    public String getBukti_trf() {
        return bukti_trf;
    }

    public void setBukti_trf(String bukti_trf) {
        this.bukti_trf = bukti_trf;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }



    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }





}
