package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rsah.koperasi.Model.Data.DataCaraPembayaran;
import com.rsah.koperasi.Model.Data.DataSaldo;

import java.util.List;

public class ResponseCaraPembayaran {

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

    public class Response {

        @SerializedName("data")
        @Expose
        private List<DataCaraPembayaran> data = null;

        public List<DataCaraPembayaran> getData() {
            return data;
        }

        public void setData(List<DataCaraPembayaran> data) {
            this.data = data;
        }


    }


}
