package com.rsah.koperasi.api;





import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.Model.ResponseDataKabupaten;
import com.rsah.koperasi.Model.ResponseDataKecamatan;
import com.rsah.koperasi.Model.ResponseDataKelurahan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiService {

    @GET("listCompany")
    Call<ResponseData> fetchCompany();


    @FormUrlEncoded
    @POST("Register")
    Call<ResponseData> Register( @Field("IdCard") String IdCard ,
                                @Field("FisrtName") String FisrtName ,
                                @Field("Gender") String Gender ,
                                @Field("Religion") String Religion ,
                                @Field("PlaceOfBirthDay") String PlaceOfBirthDay ,
                                @Field("DateOfBirthDay") String DateOfBirthDay ,
                                @Field("Address") String Address ,
                                @Field("Kelurahan") String Kelurahan ,
                                @Field("Kecamatan") String Kecamatan ,
                                @Field("City") String City ,
                                @Field("Province") String Province ,
                                @Field("CompanyCode") String CompanyCode ,
                                @Field("Email") String Email ,
                                @Field("MobilePhone") String MobilePhone ,
                                @Field("Password") String Password ,
                                @Field("ImgIDCard") String ImgIDCard ,
                                @Field("ImgFace") String ImgFace);


    @FormUrlEncoded
    @POST("login")
    Call<ResponseData> Login(@Field("username") String username , @Field("password") String password);

    @FormUrlEncoded
    @POST("ubahPwd")
    Call<ResponseData> ubahPwd(@Field("email") String mID,
                               @Field("pass_old") String pass_old,
                               @Field("pass_baru") String pass_baru);

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

    @POST("Saldo")
    Call<ResponseData> getSaldo();

    @GET("Barang")
    Call<ResponseData> getBarang();

    @FormUrlEncoded
    @POST("Peserta")
    Call<ResponseData> getProfile(@Field("memberID") String memberID);

    @FormUrlEncoded
    @POST("InsertBarangToKeranjang")
    Call<ResponseData> requestInsertKrj(@Field("IDMember") String memberID , @Field("IDBarang") String IDBarang);


    @FormUrlEncoded
    @POST("listKeranjang")
    Call<ResponseData> getKeranjang(@Field("memberID") String memberID);

    @FormUrlEncoded
    @POST("listPesanan")
    Call<ResponseData> getPesanan(@Field("memberID") String memberID);


}
