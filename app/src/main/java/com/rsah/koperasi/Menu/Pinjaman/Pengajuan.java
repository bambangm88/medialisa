package com.rsah.koperasi.Menu.Pinjaman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsah.koperasi.Auth.Register_Next_Simpan_New;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Model.Data.DataCompany;
import com.rsah.koperasi.Model.Data.DataJangkaWaktu;
import com.rsah.koperasi.Model.Data.DataNominal;
import com.rsah.koperasi.Model.Data.DataPinjaman;
import com.rsah.koperasi.Model.Response.ResponseJangkaWaktu;
import com.rsah.koperasi.Model.Response.ResponseJenisPinjaman;
import com.rsah.koperasi.Model.Response.ResponseNominalPinjaman;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;

public class Pengajuan extends AppCompatActivity {


    private RelativeLayout rlprogress ;
    private Context context ;

    private List<DataPinjaman> AllJenisPinjamanList = new ArrayList<>();
    private List<DataNominal> AllNominalPinjamanList = new ArrayList<>();
    private List<DataJangkaWaktu> AllJangkaWaktuPinjamanList = new ArrayList<>();
    private ArrayList<String> arrayJanisPinjaman = new ArrayList<String>();
    private ArrayList<String> arrayNominalPinjaman = new ArrayList<String>();
    private ArrayList<String> arrayJangkaWaktuPinjaman = new ArrayList<String>();

    
    Spinner spJenisPinjaman , spNominalPinjaman , spJangkaWaktu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);


        context = this ;
        rlprogress = findViewById(R.id.rlprogress);
        spJenisPinjaman = findViewById(R.id.jenisPinjaman);
        spNominalPinjaman = findViewById(R.id.jumlahPinjaman);
        spJangkaWaktu = findViewById(R.id.jangkaWaktu);

        
        fetchJenisPinjaman();
        fetchJumlahPinjaman();
        fetchJangkaWaktu();

    }








    private void fetchJenisPinjaman() {


        ApiService API = Server.getAPIService();
        showProgress(true);
        API.jenisPinjaman().enqueue(new Callback<ResponseJenisPinjaman>() {
            @Override
            public void onResponse(Call<ResponseJenisPinjaman> call, Response<ResponseJenisPinjaman> response) {
                if (response.isSuccessful()){
                    if (response.body().getMetadata() != null) {
                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();
                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);
                            AllJenisPinjamanList.addAll(response.body().getResponse().getData());
                            arrayJanisPinjaman.add("-- Pilih --") ;
                            for(DataPinjaman model : AllJenisPinjamanList) {
                                if(!arrayJanisPinjaman.contains(model.getJenisPinjaman())){
                                    arrayJanisPinjaman.add(model.getJenisPinjaman()) ;
                                }
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, simple_spinner_item,arrayJanisPinjaman );
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spJenisPinjaman.setAdapter(spinnerArrayAdapter);
                        }else {
                            showProgress(false);
                            Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        showProgress(false);
                        Toast.makeText(context,"Error response data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showProgress(false);
                    Toast.makeText(context,"Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJenisPinjaman> call, Throwable t) {
                showProgress(false);
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchJumlahPinjaman() {

        ApiService API = Server.getAPIService();
        showProgress(true);
        API.nominalPinjaman().enqueue(new Callback<ResponseNominalPinjaman>() {
            @Override
            public void onResponse(Call<ResponseNominalPinjaman> call, Response<ResponseNominalPinjaman> response) {
                if (response.isSuccessful()){
                    if (response.body().getMetadata() != null) {
                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();
                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);
                            AllNominalPinjamanList.addAll(response.body().getResponse().getData());
                            arrayNominalPinjaman.add("-- Pilih --") ;
                            for(DataNominal model : AllNominalPinjamanList) {
                                if(!arrayNominalPinjaman.contains(model.getNominal())){
                                    arrayNominalPinjaman.add(model.getNominal()) ;
                                }
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, simple_spinner_item,arrayNominalPinjaman );
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spNominalPinjaman.setAdapter(spinnerArrayAdapter);
                        }else {
                            showProgress(false);
                            Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        showProgress(false);
                        Toast.makeText(context,"Error response data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showProgress(false);
                    Toast.makeText(context,"Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNominalPinjaman> call, Throwable t) {
                showProgress(false);
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchJangkaWaktu() {

        ApiService API = Server.getAPIService();
        showProgress(true);
        API.jangkaWaktuPinjaman().enqueue(new Callback<ResponseJangkaWaktu>() {
            @Override
            public void onResponse(Call<ResponseJangkaWaktu> call, Response<ResponseJangkaWaktu> response) {
                if (response.isSuccessful()){
                    if (response.body().getMetadata() != null) {
                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();
                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);
                            AllJangkaWaktuPinjamanList.addAll(response.body().getResponse().getData());
                            arrayJangkaWaktuPinjaman.add("-- Pilih --") ;
                            for(DataJangkaWaktu model : AllJangkaWaktuPinjamanList) {
                                if(!arrayJangkaWaktuPinjaman.contains(model.getTenor())){
                                    arrayJangkaWaktuPinjaman.add(model.getTenor()) ;
                                }
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, simple_spinner_item,arrayJangkaWaktuPinjaman);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spJangkaWaktu.setAdapter(spinnerArrayAdapter);
                        }else {
                            showProgress(false);
                            Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        showProgress(false);
                        Toast.makeText(context,"Error response data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showProgress(false);
                    Toast.makeText(context,"Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJangkaWaktu> call, Throwable t) {
                showProgress(false);
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
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