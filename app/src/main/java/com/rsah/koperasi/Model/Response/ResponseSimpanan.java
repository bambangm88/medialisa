package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rsah.koperasi.Model.Data.DataBarang;
import com.rsah.koperasi.Model.Data.DataSimpanan;

import java.util.List;

public class ResponseSimpanan {

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
        private List<DataSimpanan> data = null;

        public List<DataSimpanan> getData() {
            return data;
        }

        public void setData(List<DataSimpanan> data) {
            this.data = data;
        }






    }


}
