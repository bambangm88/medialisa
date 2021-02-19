package com.rsah.koperasi.Menu.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Auth.Register_Next_Simpan_New;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_PRIBADI;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.Json.JsonProfile;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Json.JsonSaldo;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseRegister;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.customfonts.EditTextSFProDisplayRegular;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.bitmapUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.chooseUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.chooseUpload_set;
import static com.rsah.koperasi.Helper.Helper.imageView2Bitmap;

public class Profile extends AppCompatActivity {


    ProgressDialog pDialog;

    SessionManager sessionManager ;

    private Context mContext;
    private ApiService API;
    private ImageView profilePict ;
    private RelativeLayout rlprogress ;

    SwipeRefreshLayout refreshLayout;
    EditTextSFProDisplayRegular nama , phone , email , bod , alamat ;
    Button btn_simpan , BtnEditProfile ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        rlprogress = findViewById(R.id.rlprogress);
        mContext = this ;
        API = Server.getAPIService();
        sessionManager = new SessionManager(this);

        pDialog = new ProgressDialog(Profile.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        nama = findViewById(R.id.nama) ;
        phone = findViewById(R.id.phone) ;
        email = findViewById(R.id.email) ;
        bod = findViewById(R.id.dateBod) ;
        alamat = findViewById(R.id.alamat) ;
        profilePict = findViewById(R.id.foto) ;
        btn_simpan = findViewById(R.id.btn_simpan);

        checkProfile();


        bod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takePhotoFromCamera();
                Helper.showDateDialog(Profile.this,bod,"yyyy-MM-dd");
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageString = "" ;
                if (profilePict.getTag() == "ada"  ){
                   imageString = getStringImage(imageView2Bitmap(profilePict));
                }

                if (nama.getText().toString().equals("")){
                    nama.setError("REQUIRED");
                    return;
                }

                if (phone.getText().toString().equals("")){
                    phone.setError("REQUIRED");
                    return;
                }

                if (bod.getText().toString().equals("")){
                    bod.setError("REQUIRED");
                    return;
                }

                if (alamat.getText().toString().equals("")){
                    alamat.setError("REQUIRED");
                    return;
                }

                JsonRegister json = new JsonRegister();
                json.setFisrtName(nama.getText().toString());
                json.setMobilePhone(phone.getText().toString());
                json.setDateOfBirthDay(bod.getText().toString());
                json.setAddress(alamat.getText().toString());
                json.setMemberID(sessionManager.getKeyId());
                json.setImgFace(imageString);

                DoEdit(json,Profile.this);


            }
        });

        BtnEditProfile = findViewById(R.id.btn_edit);
        BtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnEditProfile.setVisibility(View.GONE);
                btn_simpan.setVisibility(View.VISIBLE);
                nama.setEnabled(true);
                phone.setEnabled(true);
                bod.setEnabled(true);
                alamat.setEnabled(true);

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

        profilePict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpload = "3" ;
                startActivity(new Intent(Profile.this, Foto_PRIBADI.class));
            }
        });



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
                            nama.setText(response.body().getResponse().getData().get(0).getFisrtName());
                            phone.setText(response.body().getResponse().getData().get(0).getMobilePhone());
                            email.setText(response.body().getResponse().getData().get(0).getEmail());
                            bod.setText(response.body().getResponse().getData().get(0).getTglLahir());
                            alamat.setText(response.body().getResponse().getData().get(0).getAddress());
                            alamat.setText(response.body().getResponse().getData().get(0).getAddress());

                            String url = Profile.this.getString(R.string.baseImageUrl)+sessionManager.getImageUrl() ;


                            Glide.with(Profile.this)
                                    .asBitmap()
                                    .load(url)
                                    .into(new CustomTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                                            int w = resource.getWidth() ;
                                            int h = resource.getHeight() ;

                                            if (w > h){
                                                profilePict.setImageBitmap(resource);
                                                profilePict.setRotation(90);
                                            }else{
                                                profilePict.setImageBitmap(resource);
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



    public void DoEdit(JsonRegister json, Context context){

        showProgress(true);
        Call<ResponseRegister> call = API.EditProfile(json);
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

                            new KAlertDialog(Profile.this, KAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Notification")
                                    .setContentText("Profile Berhasil di Edit")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            finish();
                                            startActivity(new Intent(Profile.this, Profile.class));
                                        }
                                    })
                                    .show();


                            btn_simpan.setVisibility(View.GONE);
                            BtnEditProfile.setVisibility(View.VISIBLE);
                            nama.setEnabled(false);
                            phone.setEnabled(false);
                            bod.setEnabled(false);
                            alamat.setEnabled(false);


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
                Helper.notifikasi_warning(t.getMessage(),context);
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
        if(chooseUpload.equals("3")){
            if(chooseUpload_set.equals("2")) {
                profilePict.setImageBitmap(bitmapUpload);
                chooseUpload = "";
                chooseUpload_set ="";
                profilePict.setTag("ada");
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

}
