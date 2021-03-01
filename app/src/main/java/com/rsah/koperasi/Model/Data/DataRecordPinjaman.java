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

    @SerializedName("CicilanReference")
    private String CicilanReference;


    @SerializedName("AngsuranKe")
    private String AngsuranKe;

    @SerializedName("JumlahDibayar")
    private String JumlahDibayar;

    @SerializedName("JatuhTempo")
    private String JatuhTempo;

    public String getCicilanReference() {
        return CicilanReference;
    }

    public void setCicilanReference(String cicilanReference) {
        CicilanReference = cicilanReference;
    }

    public String getJumlahDibayar() {
        return JumlahDibayar;
    }

    public void setJumlahDibayar(String jumlahDibayar) {
        JumlahDibayar = jumlahDibayar;
    }

    public String getJatuhTempo() {
        return JatuhTempo;
    }

    public void setJatuhTempo(String jatuhTempo) {
        JatuhTempo = jatuhTempo;
    }

    public String getAngsuranKe() {
        return AngsuranKe;
    }

    public void setAngsuranKe(String angsuranKe) {
        AngsuranKe = angsuranKe;
    }



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
