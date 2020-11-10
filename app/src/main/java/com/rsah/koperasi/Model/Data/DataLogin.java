package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataLogin {


    @SerializedName("FisrtName")
    private String FisrtName;

    @SerializedName("Email")
    private String Email;

    @SerializedName("MobilePhone")
    private String MobilePhone;

    @SerializedName("ImgFace")
    private String ImgFace;

    @SerializedName("MemberID")
    private String MemberID;


    @SerializedName("No_IDCard")
    private String No_IDCard;

    @SerializedName("CompanyName")
    private String CompanyName;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }



    public String getNo_IDCard() {
        return No_IDCard;
    }

    public void setNo_IDCard(String no_IDCard) {
        No_IDCard = no_IDCard;
    }


    public String getFisrtName() {
        return FisrtName;
    }

    public void setFisrtName(String fisrtName) {
        FisrtName = fisrtName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getImgFace() {
        return ImgFace;
    }

    public void setImgFace(String imgFace) {
        ImgFace = imgFace;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

















}
