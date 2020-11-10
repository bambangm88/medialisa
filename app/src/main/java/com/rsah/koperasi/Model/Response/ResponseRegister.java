package com.rsah.koperasi.Model.Response;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister {

    @SerializedName("metadata")
    private Metadata metadata = null;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
