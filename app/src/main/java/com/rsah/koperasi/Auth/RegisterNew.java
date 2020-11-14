package com.rsah.koperasi.Auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.Json.JsonRegistrasiEmpID;
import com.rsah.koperasi.Model.Response.ResponseRegistrasiEmpID;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterNew extends AppCompatActivity {

    ApiService API;
    private RelativeLayout rlprogress , rlprogressLoading;
    EditText nik ;
    Button searchNik ;
    Context mcontext ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registernew);
        API = Server.getAPIService();
        rlprogress = findViewById(R.id.rlprogress);
        nik = findViewById(R.id.nik);
        searchNik = findViewById(R.id.btn_search);
        mcontext = this ;

        searchNik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nik.getText().toString().equals("")){
                    nik.setError("REQUIRED");
                }else{

                    JsonRegistrasiEmpID json = new JsonRegistrasiEmpID();
                    json.setEmpID(nik.getText().toString());
                    get_nik(json,nik.getText().toString());
                }


            }
        });

    }


    private void get_nik(JsonRegistrasiEmpID json, String empid) {

        showProgress(true);

        API.cekRegistrasiByEmpID(json).enqueue(new Callback<ResponseRegistrasiEmpID>() {
            @Override
            public void onResponse(Call<ResponseRegistrasiEmpID> call, Response<ResponseRegistrasiEmpID> response) {

                if (response.isSuccessful()){

                    showProgress(false);

                    if (response.body().getMetadata() != null) {


                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {

                            Intent i = new Intent(RegisterNew.this,Register_Next_Simpan_New.class);
                            i.putExtra("nama", response.body().getResponse().getData().get(0).getsFisrtName());
                            i.putExtra("jk", response.body().getResponse().getData().get(0).getaGender());
                            i.putExtra("agama", response.body().getResponse().getData().get(0).getAgama());
                            i.putExtra("idagama", response.body().getResponse().getData().get(0).getaReligion());
                            i.putExtra("tempatlahir", response.body().getResponse().getData().get(0).getsPlaceOfBirthDay());
                            i.putExtra("tanggallahir", response.body().getResponse().getData().get(0).getdDateOfBirthDay());
                            i.putExtra("alamat", response.body().getResponse().getData().get(0).getsAddress());
                            i.putExtra("nohp", response.body().getResponse().getData().get(0).getsMobilePhone());
                            i.putExtra("nohp", response.body().getResponse().getData().get(0).getsMobilePhone());
                            i.putExtra("city", response.body().getResponse().getData().get(0).getsCity());
                            i.putExtra("state", response.body().getResponse().getData().get(0).getsState());
                            i.putExtra("idCard", empid);
                            startActivity(i);

                        }else{

                            Helper.notifikasi_warning(message,mcontext);

                        }

                    }else {
                        showProgress(false);
                        Helper.notifikasi_warning("Terjadi Gangguan",mcontext);
                    }


                }else {
                    showProgress(false);
                    Helper.notifikasi_warning("Terjadi Gangguan",mcontext);
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistrasiEmpID> call, Throwable t) {
                showProgress(false);
                Helper.notifikasi_warning(t.getMessage(),mcontext);
            }
        });
    }


    private void showProgress(Boolean bool){

        if (bool){
            rlprogress.setVisibility(View.VISIBLE);
        }else {
            rlprogress.setVisibility(View.GONE);
        }
    }















}

