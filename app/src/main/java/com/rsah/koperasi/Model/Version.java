package com.rsah.koperasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {
    @Expose
    @SerializedName("id") private String id;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("version") private String version;
    @Expose
    @SerializedName("author") private String author;
    @Expose
    @SerializedName("keterangan") private String keterangan;
    @Expose
    @SerializedName("link") private String link;

    public Version() {
    }

    public Version(String id, String nama, String version, String author, String keterangan, String link) {
        this.id = id;
        this.nama = nama;
        this.version = version;
        this.author = author;
        this.keterangan = keterangan;
        this.link = link;
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
}
