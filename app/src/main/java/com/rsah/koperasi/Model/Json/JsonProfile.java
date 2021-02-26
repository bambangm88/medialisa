package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonProfile {

    @SerializedName("memberID")
    private String memberID;

    @SerializedName("reference")
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }



    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }





}
