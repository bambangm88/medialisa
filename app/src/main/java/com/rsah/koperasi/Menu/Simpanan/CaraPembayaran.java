package com.rsah.koperasi.Menu.Simpanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.Json.JsonSimpananSukarela;
import com.rsah.koperasi.Model.Response.ResponseCaraPembayaran;
import com.rsah.koperasi.Model.Response.ResponseRegister;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaraPembayaran extends AppCompatActivity {


    private LinearLayout simpananSukarela ;
    ApiService API;
    private ImageView iv_buktitrf ;
    SessionManager sessionManager ;
    private RelativeLayout rlprogress , rlprogressLoading;
    private Button btn_wa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cara_pembayaran);
        API = Server.getAPIService();
        rlprogress = findViewById(R.id.rlprogress);
        sessionManager = new SessionManager(this);
        btn_wa = findViewById(R.id.btn_wa);
        Toolbar toolbar = findViewById(R.id.toolbar_pay);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        caraPembayaran();
    }

    public void caraPembayaran(){

        showProgress(true);
        Call<ResponseCaraPembayaran> call = API.cara_pembayaran();
        call.enqueue(new Callback<ResponseCaraPembayaran>() {
            @Override
            public void onResponse(Call<ResponseCaraPembayaran> call, Response<ResponseCaraPembayaran> response) {
                if(response.isSuccessful()) {
                    showProgress(false);

                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);


                            TextView no_rek = findViewById(R.id.tv_norek);
                            TextView nama_rek = findViewById(R.id.tv_namarek);
                            TextView bank_rek = findViewById(R.id.tv_bankrek);

                            no_rek.setText(response.body().getResponse().getData().get(0).getNo_rekening());
                            nama_rek.setText(response.body().getResponse().getData().get(0).getNama_rekening());
                            bank_rek.setText(response.body().getResponse().getData().get(0).getBank_rekening());
                            String wa = response.body().getResponse().getData().get(0).getWa_admin();


                            Button btn_salin_nominal = findViewById(R.id.btn_salin_rekening);
                            btn_salin_nominal.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String rek = no_rek.getText().toString() ;
                                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    if (clipboardManager != null) {
                                        clipboardManager.setText(rek);
                                    }
                                    Toast.makeText(getApplicationContext(), rek + " Salin Berhasil", Toast.LENGTH_SHORT).show();
                                }
                            });

                            btn_wa.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    openWA(wa);
                                }
                            });



                        }else{
                            showProgress(false);
                            // Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                            //Helper.notifikasi_warning(message,context);

                            new KAlertDialog(CaraPembayaran.this, KAlertDialog.WARNING_TYPE)
                                    .setTitleText("Notification")
                                    .setContentText("Terjadi Kesalahan")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            finish();

                                        }
                                    })
                                    .show();

                        }

                    }else{
                        showProgress(false);


                        new KAlertDialog(CaraPembayaran.this, KAlertDialog.WARNING_TYPE)
                                .setTitleText("Notification")
                                .setContentText("Terjadi Kesalahan")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                    @Override
                                    public void onClick(KAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        finish();

                                    }
                                })
                                .show();




                       // Helper.notifikasi_warning("Terjadi Gangguan",context);
                    }

                }else{
                    showProgress(false);

                    new KAlertDialog(CaraPembayaran.this, KAlertDialog.WARNING_TYPE)
                            .setTitleText("Notification")
                            .setContentText("Terjadi Kesalahan")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                @Override
                                public void onClick(KAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    finish();

                                }
                            })
                            .show();


                    //Helper.notifikasi_warning("Terjadi Gangguan",context);
                }
            }

            @Override
            public void onFailure(Call<ResponseCaraPembayaran> call, Throwable t) {
                showProgress(false);

                new KAlertDialog(CaraPembayaran.this, KAlertDialog.WARNING_TYPE)
                        .setTitleText("Notification")
                        .setContentText("Terjadi Gangguan Pada Server")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                finish();

                            }
                        })
                        .show();



                //Helper.notifikasi_warning(t.getMessage(),context);
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


    private void openWA(String noWA) {

        try {

            String number = noWA;
            String url = "https://api.whatsapp.com/send?phone="+number;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            startActivity(i);

        }catch (Exception ex){
            Toast.makeText(CaraPembayaran.this,"Whatsapp tidak ditemukan",Toast.LENGTH_LONG).show();
        }

    }



}