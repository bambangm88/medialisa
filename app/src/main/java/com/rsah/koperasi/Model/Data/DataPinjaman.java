package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataPinjaman {


    @SerializedName("id")
    private String id;

    @SerializedName("jenisPinjaman")
    private String jenisPinjaman;



    @SerializedName("ID")
    private String ID;

    @SerializedName("MemberID")
    private String MemberID;

    @SerializedName("JumlahPinjaman")
    private String JumlahPinjaman;

    @SerializedName("TanggalPinjaman")
    private String TanggalPinjaman;

    @SerializedName("Status")
    private String Status;

    @SerializedName("ImageForm")
    private String ImageForm;

    @SerializedName("CompanyCode")
    private String CompanyCode;

    @SerializedName("besarAngsuran")
    private String besarAngsuran;

    @SerializedName("lamaAngsuran")
    private String lamaAngsuran;

    @SerializedName("orderIdPinjaman")
    private String orderIdPinjaman;

    @SerializedName("StatusDesc")
    private String StatusDesc;

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getJumlahPinjaman() {
        return JumlahPinjaman;
    }

    public void setJumlahPinjaman(String jumlahPinjaman) {
        JumlahPinjaman = jumlahPinjaman;
    }

    public String getTanggalPinjaman() {
        return TanggalPinjaman;
    }

    public void setTanggalPinjaman(String tanggalPinjaman) {
        TanggalPinjaman = tanggalPinjaman;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImageForm() {
        return ImageForm;
    }

    public void setImageForm(String imageForm) {
        ImageForm = imageForm;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getBesarAngsuran() {
        return besarAngsuran;
    }

    public void setBesarAngsuran(String besarAngsuran) {
        this.besarAngsuran = besarAngsuran;
    }

    public String getLamaAngsuran() {
        return lamaAngsuran;
    }

    public void setLamaAngsuran(String lamaAngsuran) {
        this.lamaAngsuran = lamaAngsuran;
    }

    public String getOrderIdPinjaman() {
        return orderIdPinjaman;
    }

    public void setOrderIdPinjaman(String orderIdPinjaman) {
        this.orderIdPinjaman = orderIdPinjaman;
    }












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
