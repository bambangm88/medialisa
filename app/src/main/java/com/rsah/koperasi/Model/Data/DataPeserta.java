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

    @SerializedName("No_IDCard")
    private String No_IDCard;


    public String getNo_IDCard() {
        return No_IDCard;
    }

    public void setNo_IDCard(String no_IDCard) {
        No_IDCard = no_IDCard;
    }



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



    @SerializedName("bank_rek")
    private String bank_rek;

    @SerializedName("nama_rek")
    private String nama_rek;

    @SerializedName("no_rek")
    private String no_rek;


    @SerializedName("no_ktp")
    private String no_ktp;

    @SerializedName("ImgFace")
    private String ImgFace;

    public String getImgFace() {
        return ImgFace;
    }

    public void setImgFace(String imgFace) {
        ImgFace = imgFace;
    }



    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public String getBank_rek() {
        return bank_rek;
    }

    public void setBank_rek(String bank_rek) {
        this.bank_rek = bank_rek;
    }

    public String getNama_rek() {
        return nama_rek;
    }

    public void setNama_rek(String nama_rek) {
        this.nama_rek = nama_rek;
    }

    public String getNo_rek() {
        return no_rek;
    }

    public void setNo_rek(String no_rek) {
        this.no_rek = no_rek;
    }





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
