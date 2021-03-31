package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonCollection {

    @SerializedName("page_num")
    private int page_num;

    @SerializedName("page_size")
    private int page_size;

    @SerializedName("id_ws")
    private int id_ws;

    public int getId_ws() {
        return id_ws;
    }

    public void setId_ws(int id_ws) {
        this.id_ws = id_ws;
    }





    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }






}
