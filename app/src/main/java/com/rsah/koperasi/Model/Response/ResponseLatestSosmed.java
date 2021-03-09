package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLatestSosmed {

    @SerializedName("status")
    private String status = null;

    @SerializedName("data_latest_fb")
    private List<data_latest_sosmed> data_latest_fb = null;

    @SerializedName("data_latest_ig")
    private List<data_latest_sosmed> data_latest_ig = null;


    @SerializedName("data_latest_twitter")
    private List<data_latest_sosmed> data_latest_twitter = null;


    @SerializedName("data_latest_youtube")
    private List<data_latest_sosmed> data_latest_youtube = null;

    @SerializedName("data_latest_forum")
    private List<data_latest_sosmed> data_latest_forum = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<data_latest_sosmed> getData_latest_fb() {
        return data_latest_fb;
    }

    public void setData_latest_fb(List<data_latest_sosmed> data_latest_fb) {
        this.data_latest_fb = data_latest_fb;
    }

    public List<data_latest_sosmed> getData_latest_ig() {
        return data_latest_ig;
    }

    public void setData_latest_ig(List<data_latest_sosmed> data_latest_ig) {
        this.data_latest_ig = data_latest_ig;
    }

    public List<data_latest_sosmed> getData_latest_twitter() {
        return data_latest_twitter;
    }

    public void setData_latest_twitter(List<data_latest_sosmed> data_latest_twitter) {
        this.data_latest_twitter = data_latest_twitter;
    }

    public List<data_latest_sosmed> getData_latest_youtube() {
        return data_latest_youtube;
    }

    public void setData_latest_youtube(List<data_latest_sosmed> data_latest_youtube) {
        this.data_latest_youtube = data_latest_youtube;
    }

    public List<data_latest_sosmed> getData_latest_forum() {
        return data_latest_forum;
    }

    public void setData_latest_forum(List<data_latest_sosmed> data_latest_forum) {
        this.data_latest_forum = data_latest_forum;
    }




    public class data_latest_sosmed {

        @SerializedName("post_url")
        private String post_url;

        @SerializedName("tanggal_posting")
        private String tanggal_posting;



        public String getPost_url() {
            return post_url;
        }

        public void setPost_url(String post_url) {
            this.post_url = post_url;
        }

        public String getTanggal_posting() {
            return tanggal_posting;
        }

        public void setTanggal_posting(String tanggal_posting) {
            this.tanggal_posting = tanggal_posting;
        }


    }



}
