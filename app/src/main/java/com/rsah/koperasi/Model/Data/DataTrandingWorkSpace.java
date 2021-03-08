package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataTrandingWorkSpace {


    @SerializedName("nama_workspace")
    private String nama_workspace;

    @SerializedName("gambar_workspace")
    private String gambar_workspace;

    @SerializedName("keterangan")
    private String keterangan;


    @SerializedName("kode_workspace")
    private String kode_workspace;

    @SerializedName("warna")
    private String warna;


    @SerializedName("id_workspace")
    private String id_workspace;


    public String getNama_workspace() {
        return nama_workspace;
    }

    public void setNama_workspace(String nama_workspace) {
        this.nama_workspace = nama_workspace;
    }

    public String getGambar_workspace() {
        return gambar_workspace;
    }

    public void setGambar_workspace(String gambar_workspace) {
        this.gambar_workspace = gambar_workspace;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKode_workspace() {
        return kode_workspace;
    }

    public void setKode_workspace(String kode_workspace) {
        this.kode_workspace = kode_workspace;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getId_workspace() {
        return id_workspace;
    }

    public void setId_workspace(String id_workspace) {
        this.id_workspace = id_workspace;
    }

































}
