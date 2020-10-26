package com.rsah.koperasi.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityBarang {


    @SerializedName("IDBarang")
    private String IDBarang;

    @SerializedName("NamaBarang")
    private String NamaBarang;

    @SerializedName("Categori")
    private String Categori;

    @SerializedName("Jenis")
    private String Jenis;

    @SerializedName("Harga")
    private String Harga;

    @SerializedName("Status")
    private String Status;


    @SerializedName("DescBarang")
    private String DescBarang;


    @SerializedName("urlImage")
    private String urlImage;




    @SerializedName("JmlBarang")
    private String JmlBarang;

    @SerializedName("Approve")
    private String Approve;

    @SerializedName("TglPesanan")
    private String TglPesanan;

    @SerializedName("IDPesanan")
    private String IDPesanan;

    @SerializedName("Reference")
    private String Reference;


    public String getJmlBarang() {
        return JmlBarang;
    }

    public void setJmlBarang(String jmlBarang) {
        JmlBarang = jmlBarang;
    }

    public String getApprove() {
        return Approve;
    }

    public void setApprove(String approve) {
        Approve = approve;
    }

    public String getTglPesanan() {
        return TglPesanan;
    }

    public void setTglPesanan(String tglPesanan) {
        TglPesanan = tglPesanan;
    }

    public String getIDPesanan() {
        return IDPesanan;
    }

    public void setIDPesanan(String IDPesanan) {
        this.IDPesanan = IDPesanan;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }











    public String getDescBarang() {
        return DescBarang;
    }

    public void setDescBarang(String descBarang) {
        DescBarang = descBarang;
    }


    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }



    public String getIDBarang() {
        return IDBarang;
    }

    public void setIDBarang(String IDBarang) {
        this.IDBarang = IDBarang;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        NamaBarang = namaBarang;
    }

    public String getCategori() {
        return Categori;
    }

    public void setCategori(String categori) {
        Categori = categori;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }




































}
