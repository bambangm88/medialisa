package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonProfile {

    @SerializedName("memberID")
    private String memberID;

    @SerializedName("reference")
    private String reference;

    @SerializedName("CicilanReference")
    private String CicilanReference;


    public String getCicilanReference() {
        return CicilanReference;
    }

    public void setCicilanReference(String cicilanReference) {
        CicilanReference = cicilanReference;
    }


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
