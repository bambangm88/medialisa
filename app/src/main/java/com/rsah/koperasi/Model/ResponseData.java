package com.rsah.koperasi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseData {

    @SerializedName("dataRegister")
    @Expose
    private List<ResponseRegister> dataRegister = null;
    public List<ResponseRegister> getDataRegister() {
        return dataRegister;
    }

    public void setDataRegister(List<ResponseRegister> dataRegister) {
        this.dataRegister = dataRegister;
    }


    @SerializedName("dataCompany")
    @Expose
    private List<ResponseCompany> dataCompany = null;

    public List<ResponseCompany> getDataCompany() {
        return dataCompany;
    }

    public void setDataCompany(List<ResponseCompany> dataCompany) {
        this.dataCompany = dataCompany;
    }

    @SerializedName("dataLogin")
    @Expose
    private List<ResponseEntityLogin> dataLogin = null;

    public List<ResponseEntityLogin> getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(List<ResponseEntityLogin> dataLogin) {
        this.dataLogin = dataLogin;
    }

    @SerializedName("Success")
    @Expose
    private String Success ;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    @SerializedName("token")
    @Expose
    private String token ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    @SerializedName("data")
    @Expose
    private List<ResponseWilayah> data = null;


    public List<ResponseWilayah> getData() {
        return data;
    }

    public void setData(List<ResponseWilayah> data) {
        this.data = data;
    }




    @SerializedName("dataBarang")
    @Expose
    private List<ResponseEntityBarang> dataBarang = null;
    public List<ResponseEntityBarang> getDataBarang() {
        return dataBarang;
    }

    public void setDataBarang(List<ResponseEntityBarang> dataBarang) {
        this.dataBarang = dataBarang;
    }


    @SerializedName("dataSaldo")
    @Expose
    private List<ResponseEntitySaldo> dataSaldo = null;

    public List<ResponseEntitySaldo> getDataSaldo() {
        return dataSaldo;
    }

    public void setDataSaldo(List<ResponseEntitySaldo> dataSaldo) {
        this.dataSaldo = dataSaldo;
    }


    @SerializedName("dataPeserta")
    @Expose
    private List<ResponseEntityPeserta> dataPeserta = null;

    public List<ResponseEntityPeserta> getDataPeserta() {
        return dataPeserta;
    }

    public void setDataPeserta(List<ResponseEntityPeserta> dataPeserta) {
        this.dataPeserta = dataPeserta;
    }

    @SerializedName("dataKeranjang")
    @Expose
    private List<ResponseEntityBarang> dataKeranjang = null;


    public List<ResponseEntityBarang> getDataKeranjang() {
        return dataKeranjang;
    }

    public void setDataKeranjang(List<ResponseEntityBarang> dataKeranjang) {
        this.dataKeranjang = dataKeranjang;
    }




    @SerializedName("dataPesanan")
    @Expose
    private List<ResponseEntityBarang> dataPesanan= null;

    public List<ResponseEntityBarang> getDataPesanan() {
        return dataPesanan;
    }

    public void setDataPesanan(List<ResponseEntityBarang> dataPesanan) {
        this.dataPesanan = dataPesanan;
    }















}







