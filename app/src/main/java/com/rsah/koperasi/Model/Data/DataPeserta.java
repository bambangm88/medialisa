package com.rsah.koperasi.Model.Data;

import com.google.gson.annotations.SerializedName;

public class DataPeserta {


    @SerializedName("MemberID")
    private String MemberID;
    @SerializedName("FisrtName")
    private String FisrtName;
    @SerializedName("DateOfBirthDay")
    private String DateOfBirthDay;
    @SerializedName("Address")
    private String Address;
    @SerializedName("Email")
    private String Email;

    @SerializedName("MobilePhone")
    private String MobilePhone;

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }



    @SerializedName("tglLahir")
    private String tglLahir;


    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getFisrtName() {
        return FisrtName;
    }

    public void setFisrtName(String fisrtName) {
        FisrtName = fisrtName;
    }

    public String getDateOfBirthDay() {
        return DateOfBirthDay;
    }

    public void setDateOfBirthDay(String dateOfBirthDay) {
        DateOfBirthDay = dateOfBirthDay;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

































}
