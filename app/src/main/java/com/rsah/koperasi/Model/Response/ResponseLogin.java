package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rsah.koperasi.Model.Data.DataLogin;

import java.util.List;

public class ResponseLogin {

    @SerializedName("metadata")
    private Metadata metadata = null;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @SerializedName("response")
    private Response response = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }




    @SerializedName("access_token")
    private String access_token;

    @SerializedName("refresh_token")
    private String refresh_token;

    @SerializedName("status")
    private String status;

    @SerializedName("Authorization")
    private String Authorization;

    @SerializedName("msg")
    private String msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }


    public class Response {

        @SerializedName("data")
        @Expose
        private List<DataLogin> data = null;

        public List<DataLogin> getData() {
            return data;
        }

        public void setData(List<DataLogin> data) {
            this.data = data;
        }




    }


}
