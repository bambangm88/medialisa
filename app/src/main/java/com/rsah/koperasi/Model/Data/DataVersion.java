package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataVersion {



    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("version")
    private String version;

    @SerializedName("author")
    private String author;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("link")
    private String link;

    @SerializedName("app_identity")
    private String app_identity;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getApp_identity() {
        return app_identity;
    }

    public void setApp_identity(String app_identity) {
        this.app_identity = app_identity;
    }
































}
