package com.rsah.koperasi.api;





import com.rsah.koperasi.Model.Json.JsonInsertBarang;
import com.rsah.koperasi.Model.Json.JsonKeranjang;
import com.rsah.koperasi.Model.Json.JsonLogin;
import com.rsah.koperasi.Model.Json.JsonPesanan;
import com.rsah.koperasi.Model.Json.JsonProfile;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Json.JsonRegistrasiEmpID;
import com.rsah.koperasi.Model.Json.JsonSaldo;
import com.rsah.koperasi.Model.Json.JsonUbahPwd;
import com.rsah.koperasi.Model.Response.ResponseBarang;
import com.rsah.koperasi.Model.Response.ResponseCompany;
import com.rsah.koperasi.Model.Response.ResponseInsertBarang;
import com.rsah.koperasi.Model.Response.ResponseKeranjang;
import com.rsah.koperasi.Model.Response.ResponseLogin;
import com.rsah.koperasi.Model.Response.ResponsePesanan;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseRegister;
import com.rsah.koperasi.Model.Response.ResponseRegistrasiEmpID;
import com.rsah.koperasi.Model.Response.ResponseSaldo;
import com.rsah.koperasi.Model.Response.ResponseUbahPwd;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.Model.ResponseDataKabupaten;
import com.rsah.koperasi.Model.ResponseDataKecamatan;
import com.rsah.koperasi.Model.ResponseDataKelurahan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiService {



    @Headers("Content-Type: application/json")
    @POST("koperasi/listCompany")
    Call<ResponseCompany> fetchCompany();


    @Headers("Content-Type: application/json")
    @POST("koperasi/Register")
    Call<ResponseRegister> Register(@Body JsonRegister body);


    @Headers("Content-Type: application/json")
    @POST("koperasi/login")
    Call<ResponseLogin> Login(@Body JsonLogin body);


    @Headers("Content-Type: application/json")
    @POST("koperasi/UbahPwd")
    Call<ResponseUbahPwd> ubahPwd(@Body JsonUbahPwd body);

    @GET("poe")
    Call<ResponseData> Token();

    @GET()
    Call<ResponseData> Wilayah(@Url String url);

    @GET()
    Call<ResponseDataKabupaten> Kabupaten(@Url String url);

    @GET()
    Call<ResponseDataKecamatan> Kecamatan(@Url String url);


    @GET()
    Call<ResponseDataKelurahan> Kelurahan(@Url String url);


    @Headers("Content-Type: application/json")
    @POST("koperasi/saldo")
    Call<ResponseSaldo> getSaldo(@Body JsonSaldo body);


    @Headers("Content-Type: application/json")
    @POST("koperasi/Barang")
    Call<ResponseBarang> getBarang();


    @Headers("Content-Type: application/json")
    @POST("koperasi/peserta")
    Call<ResponseProfile> getProfile(@Body JsonProfile body);


    @Headers("Content-Type: application/json")
    @POST("koperasi/InsertBarangToKeranjang")
    Call<ResponseInsertBarang> requestInsertKrj(@Body JsonInsertBarang body);

    @Headers("Content-Type: application/json")
    @POST("koperasi/ListKeranjang")
    Call<ResponseKeranjang> getKeranjang(@Body JsonKeranjang body);

    @Headers("Content-Type: application/json")
    @POST("koperasi/listPesanan")
    Call<ResponsePesanan> getPesanan(@Body JsonPesanan body);


    @Headers("Content-Type: application/json")
    @POST("koperasi/CekRegistrasiByEmpID")
    Call<ResponseRegistrasiEmpID> cekRegistrasiByEmpID(@Body JsonRegistrasiEmpID body);




}
