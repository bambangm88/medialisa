package com.rsah.koperasi.Model.Json;

import com.google.gson.annotations.SerializedName;

public class JsonProfile {

    @SerializedName("memberID")
    private String memberID;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }





}
