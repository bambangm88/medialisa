package com.rsah.koperasi.Auth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        Toolbar toolbar = findViewById(R.id.toolbar_pay);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                            i.putExtra("nik", response.body().getResponse().getData().get(0).getNik());
                            i.putExtra("name", response.body().getResponse().getData().get(0).getName());
                            i.putExtra("unitName", response.body().getResponse().getData().get(0).getUnitName());
                            i.putExtra("unitCode", response.body().getResponse().getData().get(0).getUnitCode());

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
                Helper.notifikasi_warning("Terjadi Gangguan Pada Server",mcontext);
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





    //homeback
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }









}

