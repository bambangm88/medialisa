package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataRecordPinjaman {


    @SerializedName("OrderIdPinjaman")
    private String OrderIdPinjaman;

    @SerializedName("ID")
    private String ID;

    @SerializedName("Status")
    private String Status;


    @SerializedName("StatusDesc")
    private String StatusDesc;

    @SerializedName("Deskripsi")
    private String Deskripsi;

    @SerializedName("ModifiedDate")
    private String ModifiedDate;


    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }




    public String getOrderIdPinjaman() {
        return OrderIdPinjaman;
    }

    public void setOrderIdPinjaman(String orderIdPinjaman) {
        OrderIdPinjaman = orderIdPinjaman;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }




    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }






























}
