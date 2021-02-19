package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataSimpanan {


    @SerializedName("MemberID")
    private String MemberID;

    @SerializedName("tgl_trf")
    private String tgl_trf;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("bukti_trf")
    private String bukti_trf;

    @SerializedName("status")
    private String status;

    @SerializedName("CompanyCode")
    private String CompanyCode;

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getTgl_trf() {
        return tgl_trf;
    }

    public void setTgl_trf(String tgl_trf) {
        this.tgl_trf = tgl_trf;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getBukti_trf() {
        return bukti_trf;
    }

    public void setBukti_trf(String bukti_trf) {
        this.bukti_trf = bukti_trf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }













}
