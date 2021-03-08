package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rsah.koperasi.Model.Data.DataPeserta;
import com.rsah.koperasi.Model.Data.DataTrandingWorkSpace;

import java.util.List;

public class ResponsTrandingWorkSpace {


    @SerializedName("status")
    private String status= null;


    @SerializedName("data_tranding")
    private List<DataTrandingWorkSpace>data_tranding = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataTrandingWorkSpace> getData_tranding() {
        return data_tranding;
    }

    public void setData_tranding(List<DataTrandingWorkSpace> data_tranding) {
        this.data_tranding = data_tranding;
    }







}
