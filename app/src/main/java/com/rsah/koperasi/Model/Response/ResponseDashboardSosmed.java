package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rsah.koperasi.Model.Data.DataRecordPinjaman;

import java.util.List;

public class ResponseDashboardSosmed {

    @SerializedName("status")
    private String status = null;

    @SerializedName("periode")
    private String periode = null;

    @SerializedName("data_persen_fb")
    private List<data_dashboard_sosmed> data_persen_fb = null;

    @SerializedName("data_persen_twitter")
    private List<data_dashboard_sosmed> data_persen_twitter = null;

    @SerializedName("data_persen_ig")
    private List<data_dashboard_sosmed> data_persen_ig= null;

    @SerializedName("data_persen_youtube")
    private List<data_dashboard_sosmed> data_persen_youtube = null;

    @SerializedName("data_persen_forum")
    private List<data_dashboard_sosmed> data_persen_forum = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public List<data_dashboard_sosmed> getData_persen_fb() {
        return data_persen_fb;
    }

    public void setData_persen_fb(List<data_dashboard_sosmed> data_persen_fb) {
        this.data_persen_fb = data_persen_fb;
    }

    public List<data_dashboard_sosmed> getData_persen_twitter() {
        return data_persen_twitter;
    }

    public void setData_persen_twitter(List<data_dashboard_sosmed> data_persen_twitter) {
        this.data_persen_twitter = data_persen_twitter;
    }

    public List<data_dashboard_sosmed> getData_persen_ig() {
        return data_persen_ig;
    }

    public void setData_persen_ig(List<data_dashboard_sosmed> data_persen_ig) {
        this.data_persen_ig = data_persen_ig;
    }

    public List<data_dashboard_sosmed> getData_persen_youtube() {
        return data_persen_youtube;
    }

    public void setData_persen_youtube(List<data_dashboard_sosmed> data_persen_youtube) {
        this.data_persen_youtube = data_persen_youtube;
    }

    public List<data_dashboard_sosmed> getData_persen_forum() {
        return data_persen_forum;
    }

    public void setData_persen_forum(List<data_dashboard_sosmed> data_persen_forum) {
        this.data_persen_forum = data_persen_forum;
    }




    public class data_dashboard_sosmed {


        @SerializedName("nama_akun")
        private String nama_akun;

        @SerializedName("total_number")
        private String total_number;

        @SerializedName("percent_number")
        private String percent_number;

        public String getNama_akun() {
            return nama_akun;
        }

        public void setNama_akun(String nama_akun) {
            this.nama_akun = nama_akun;
        }

        public String getTotal_number() {
            return total_number;
        }

        public void setTotal_number(String total_number) {
            this.total_number = total_number;
        }

        public String getPercent_number() {
            return percent_number;
        }

        public void setPercent_number(String percent_number) {
            this.percent_number = percent_number;
        }




    }



}
