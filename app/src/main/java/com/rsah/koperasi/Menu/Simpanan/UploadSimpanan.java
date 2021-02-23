package com.rsah.koperasi.Menu.Simpanan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Auth.Register_Next_Simpan_New;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_Bukti_Transfer;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_PRIBADI;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Profile.Profile;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Json.JsonSimpananSukarela;
import com.rsah.koperasi.Model.Response.ResponseRegister;
import com.rsah.koperasi.Model.Response.ResponseSimpanan;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.bitmapUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.chooseUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.chooseUpload_set;
import static com.rsah.koperasi.Helper.Helper.imageView2Bitmap;

public class UploadSimpanan extends AppCompatActivity {


    private LinearLayout simpananSukarela ;
    ApiService API;
    private ImageView iv_buktitrf ;
    SessionManager sessionManager ;
    private RelativeLayout rlprogress , rlprogressLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpanan_sukarela);

        API = Server.getAPIService();
        rlprogress = findViewById(R.id.rlprogress);
        sessionManager = new SessionManager(this);

        EditText et_jumlah = findViewById(R.id.et_jumlah) ;
        EditText et_trf = findViewById(R.id.et_trf) ;
        iv_buktitrf = findViewById(R.id.iv_bukti_trf) ;
        Button btn_simpan = findViewById(R.id.btn_simpan) ;
        Button cara_pembayaran = findViewById(R.id.cara_pembayaran) ;


        Toolbar toolbar = findViewById(R.id.toolbar_pay);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cara_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takePhotoFromCamera();
                startActivity(new Intent(UploadSimpanan.this,CaraPembayaran.class));
            }
        });

        et_trf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takePhotoFromCamera();
                Helper.showDateDialog(UploadSimpanan.this,et_trf,"yyyy-MM-dd hh:mm:ss");
            }
        });

        iv_buktitrf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpload = "4" ;
                startActivity(new Intent(UploadSimpanan.this, Foto_Bukti_Transfer.class));
            }
        });


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageString = "" ;
                if (iv_buktitrf.getTag() == "ada"  ){
                    imageString = getStringImage(imageView2Bitmap(iv_buktitrf));
                }

                String _et_jumlah = et_jumlah.getText().toString() ;

                if(et_jumlah.getText().toString().equals("")){
                    _et_jumlah = "0" ;
                }

                if(Integer.parseInt(_et_jumlah) == 0){
                    Toast.makeText(UploadSimpanan.this,"Silahkan Masukan Jumlah Transfer",Toast.LENGTH_LONG).show();
                    return;
                }

                if(et_trf.getText().toString().equals("")){
                    Toast.makeText(UploadSimpanan.this,"Silahkan Masukan Tanggal",Toast.LENGTH_LONG).show();
                    return;
                }

                JsonSimpananSukarela json = new JsonSimpananSukarela();
                json.setMemberID(sessionManager.getKeyId());
                json.setCompanyCode(sessionManager.getKeyCompanyCode());
                json.setBukti_trf(imageString);
                json.setJumlah(_et_jumlah);
                json.setTgl_trf(et_trf.getText().toString());
                DoSimpanaan(json,UploadSimpanan.this);

            }
        });








    }

    public void DoSimpanaan(JsonSimpananSukarela json, Context context){

        showProgress(true);
        Call<ResponseRegister> call = API.addSimpananSukarela(json);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if(response.isSuccessful()) {
                    showProgress(false);

                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);

                            new KAlertDialog(UploadSimpanan.this, KAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Notification")
                                    .setContentText("Upload Bukti Simpanan Berhasil")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            finish();
                                            startActivity(new Intent(UploadSimpanan.this, Login.class));
                                        }
                                    })
                                    .show();


                        }else{
                            showProgress(false);
                            // Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                            Helper.notifikasi_warning(message,context);

                        }

                    }else{
                        showProgress(false);
                        Helper.notifikasi_warning("Terjadi Gangguan",context);
                    }

                }else{
                    showProgress(false);
                    Helper.notifikasi_warning("Terjadi Gangguan",context);
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                showProgress(false);
                Helper.notifikasi_warning("Terjadi Gangguan Pada Server",context);
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


    @Override
    protected void onResume() {
        super.onResume();
        iv_buktitrf = findViewById(R.id.iv_bukti_trf) ;

        if (chooseUpload.equals("4")) {
            if (chooseUpload_set.equals("4")) {
                iv_buktitrf.setImageBitmap(bitmapUpload);
                chooseUpload = "";
                chooseUpload_set = "";
                iv_buktitrf.setTag("ada");
            }
        }

    }

    public String getStringImage(Bitmap bmp) {
        int bitmap_size = 60; // range 1 - 100
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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