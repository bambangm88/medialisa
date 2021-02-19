package com.rsah.koperasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Barang.MenuBarang;
import com.rsah.koperasi.Menu.Pengaturan;
import com.rsah.koperasi.Menu.Pinjaman.MainPinjaman;
import com.rsah.koperasi.Menu.Profile.Profile;
import com.rsah.koperasi.Menu.Saldo.DetailSaldo;

import com.rsah.koperasi.Menu.Simpanan.Simpanan;
import com.rsah.koperasi.Menu.Simpanan.listSimpanan;
import com.rsah.koperasi.Model.Json.JsonProfile;
import com.rsah.koperasi.Model.Json.JsonSaldo;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseSaldo;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    TextView txtUser , txtTelp , id_karyawan ;

    ImageView iv_face ;

    ProgressDialog pDialog;

    SessionManager sessionManager ;

    CardView cvSetting, cvPeserta , cvBarang , cvSaldo , cvPInjaman , cvKeluar , cvSimpanan ;

    private Context mContext;
    private ApiService API;

    public static String saldo ;

    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        txtUser = findViewById(R.id.Username);
       // txtEmail = findViewById(R.id.email);
        txtTelp = findViewById(R.id.telp);
        id_karyawan= findViewById(R.id.id_karyawan);
        //id_koperasi= findViewById(R.id.id_koperasi);

        cvSetting = findViewById(R.id.cv_Setting);
        cvPeserta= findViewById(R.id.cv_Peserta);
        cvBarang= findViewById(R.id.cv_barang);
        cvSaldo= findViewById(R.id.card_saldo);
        cvKeluar= findViewById(R.id.cv_keluar);
        cvSimpanan= findViewById(R.id.cv_simpanan);
       // cvPInjaman= findViewById(R.id.cv_Pinjaman);

        iv_face = findViewById(R.id.iv_face);

        Toolbar toolbar ;
        toolbar = (Toolbar) findViewById(R.id.toolbar_pay);
        setSupportActionBar(toolbar);


       // txtEmail.setText(sessionManager.getKeyEmail());
        txtUser.setText(sessionManager.getUsername());
        txtTelp.setText(sessionManager.getNoTelp() + " | " + sessionManager.getKeyEmail());
        id_karyawan.setText("ID KARY : "+sessionManager.getKeyIdCard() + " | "+ "ID KOP : "+sessionManager.getKeyId());
        //id_koperasi.setText("ID KOP : "+sessionManager.getKeyId());

        String url = this.getString(R.string.baseImageUrl)+sessionManager.getImageUrl() ;

        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        int w = resource.getWidth() ;
                        int h = resource.getHeight() ;

                        if (w > h){
                            iv_face.setImageBitmap(resource);
                            iv_face.setRotation(90);
                        }else{
                            iv_face.setImageBitmap(resource);
                        }


                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });













        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");



        cvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Pengaturan.class));

            }
        });

        cvPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Profile.class));

            }
        });

       /* cvPInjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MainPinjaman.class));

            }
        });*/

       cvSimpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // startActivity(new Intent(MainActivity.this, Simpanan.class));
                startActivity(new Intent(MainActivity.this, listSimpanan.class));

            }
        });


        cvBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MenuBarang.class));

            }
        });


        cvSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DetailSaldo.class);
                i.putExtra("idkop", sessionManager.getKeyId());
                i.putExtra("companyname", sessionManager.getKeyIdCompany());
                i.putExtra("name", sessionManager.getUsername());
                i.putExtra("saldo", saldo);


                mContext.startActivity(i);


            }
        });


        cvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_dialog();

            }
        });

        refreshLayout = findViewById(R.id.swipe_to_refresh_layout);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_green_dark, android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkProfile();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        checkProfile();


    }


    public void show_dialog(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SessionManager sessionManager = new SessionManager(MainActivity.this);
                        sessionManager.logoutUser();
                        Intent intent = new Intent(MainActivity.this, Login.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();


                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void checkProfile(){

        JsonProfile json = new JsonProfile();
        json.setMemberID(sessionManager.getKeyId());
        pDialog.show();
        Call<ResponseProfile> call = API.getProfile(json);
        call.enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage() ;
                        String status = response.body().getMetadata().getCode() ;

                        if(status.equals(Constant.ERR_200)){

                            pDialog.cancel();

                            // txtEmail.setText(sessionManager.getKeyEmail());
                            txtUser.setText(response.body().getResponse().getData().get(0).getFisrtName());
                            txtTelp.setText(response.body().getResponse().getData().get(0).getMobilePhone() + " | " + response.body().getResponse().getData().get(0).getEmail());
                            id_karyawan.setText("ID KARY : "+response.body().getResponse().getData().get(0).getNo_IDCard() + " | "+ "ID KOP : "+response.body().getResponse().getData().get(0).getMemberID());
                            //id_koperasi.setText("ID KOP : "+sessionManager.getKeyId());

                            String url = MainActivity.this.getString(R.string.baseImageUrl)+sessionManager.getImageUrl() ;

                            Glide.with(MainActivity.this)
                                    .asBitmap()
                                    .load(url)
                                    .into(new CustomTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                                            int w = resource.getWidth() ;
                                            int h = resource.getHeight() ;

                                            if (w > h){
                                                iv_face.setImageBitmap(resource);
                                                iv_face.setRotation(90);
                                            }else{
                                                iv_face.setImageBitmap(resource);
                                            }


                                        }

                                        @Override
                                        public void onLoadCleared(@Nullable Drawable placeholder) {
                                        }
                                    });




                        }else{
                            pDialog.cancel();
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }else{
                        pDialog.cancel();
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                }else{
                    pDialog.cancel();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }




}
