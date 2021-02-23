package com.rsah.koperasi.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_PRIBADI;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Profile.Profile;
import com.rsah.koperasi.Model.Json.JsonProfile;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseRegister;
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


public class ProfilPribadiFragment extends Fragment {



    ProgressDialog pDialog;

    SessionManager sessionManager ;

    private Context mContext;
    private ApiService API;
    private ImageView profilePict ;
    private RelativeLayout rlprogress ;

    SwipeRefreshLayout refreshLayout;
    EditTextSFProDisplayRegular nama , phone , email , bod , alamat , ktp ;
    Button btn_simpan , BtnEditProfile ;


    public ProfilPribadiFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profil_pribadi, container, false);





        rlprogress = root.findViewById(R.id.rlprogress);
        mContext = getActivity() ;
        API = Server.getAPIService();
        sessionManager = new SessionManager(mContext);

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        nama = root.findViewById(R.id.nama) ;
        phone = root.findViewById(R.id.phone) ;
        email = root.findViewById(R.id.email) ;
        bod = root.findViewById(R.id.dateBod) ;
        alamat = root.findViewById(R.id.alamat) ;
        profilePict = root.findViewById(R.id.foto) ;
        btn_simpan = root.findViewById(R.id.btn_simpan);
        ktp = root.findViewById(R.id.ktp);



        checkProfile();


        bod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takePhotoFromCamera();
                Helper.showDateDialog(getActivity(),bod,"yyyy-MM-dd");
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageString = "" ;
                if (profilePict.getTag() == "ada"  ){
                    imageString = getStringImage(imageView2Bitmap(profilePict));
                    profilePict.setTag("");
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
                if (ktp.getText().toString().equals("")){
                    ktp.setError("REQUIRED");
                    return;
                }

                JsonRegister json = new JsonRegister();
                json.setFisrtName(nama.getText().toString());
                json.setMobilePhone(phone.getText().toString());
                json.setDateOfBirthDay(bod.getText().toString());
                json.setAddress(alamat.getText().toString());
                json.setMemberID(sessionManager.getKeyId());
                json.setImgFace(imageString);
                json.setKtp(ktp.getText().toString());
                json.setType("profile");

                DoEdit(json,getActivity());


            }
        });

        BtnEditProfile = root.findViewById(R.id.btn_edit);
        BtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnEditProfile.setVisibility(View.GONE);
                btn_simpan.setVisibility(View.VISIBLE);
                nama.setEnabled(true);
                phone.setEnabled(true);
                bod.setEnabled(true);
                alamat.setEnabled(true);
                nama.setFocusable(true);
                phone.setFocusable(true);
                alamat.setFocusable(true);
                profilePict.setEnabled(true);
                ktp.setEnabled(true);
                Toast.makeText(mContext, "Silahkan Edit Profile", Toast.LENGTH_LONG).show();

            }
        });

        /*refreshLayout = root.findViewById(R.id.swipe_to_refresh_layout);
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
                }, 1000);
            }
        });*/
        profilePict.setEnabled(false);
        profilePict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpload = "3" ;
                startActivity(new Intent(getActivity(), Foto_PRIBADI.class));
            }
        });


        return root;
    }


    private void checkProfile(){

        JsonProfile json = new JsonProfile();
        json.setMemberID(sessionManager.getKeyId());
        //pDialog.show();
        showProgress(true);
        Call<ResponseProfile> call = API.getProfile(json);
        call.enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage() ;
                        String status = response.body().getMetadata().getCode() ;

                        if(status.equals(Constant.ERR_200)){

                            // pDialog.cancel();
                            showProgress(false);
                            nama.setText(response.body().getResponse().getData().get(0).getFisrtName());
                            phone.setText(response.body().getResponse().getData().get(0).getMobilePhone());
                            email.setText(response.body().getResponse().getData().get(0).getEmail());
                            bod.setText(response.body().getResponse().getData().get(0).getTglLahir());
                            alamat.setText(response.body().getResponse().getData().get(0).getAddress());
                            ktp.setText(response.body().getResponse().getData().get(0).getNo_ktp());

                            String imgFace =response.body().getResponse().getData().get(0).getImgFace() ;

                            String url = getActivity().getString(R.string.baseImageUrl)+imgFace ;

                            if (!imgFace.equals("")) {
                                Glide.with(getActivity())
                                        .asBitmap()
                                        .load(url)
                                        .into(new CustomTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                                                int w = resource.getWidth();
                                                int h = resource.getHeight();

                                                if (w > h) {
                                                    profilePict.setImageBitmap(resource);
                                                    profilePict.setRotation(90);
                                                } else {
                                                    profilePict.setImageBitmap(resource);
                                                }


                                            }

                                            @Override
                                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                            }
                                        });
                            }



                        }else{
                            //pDialog.cancel();
                            showProgress(false);
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                            //finish();
                        }

                    }else{
                        //pDialog.cancel();
                        showProgress(false);
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                }else{
                    //pDialog.cancel();
                    showProgress(false);
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable t) {

                //pDialog.cancel();
                showProgress(false);
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+"Terjadi Gangguan Pada Server" );
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

                            new KAlertDialog(getActivity(), KAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Notification")
                                    .setContentText("Profile Berhasil di Edit")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            //finish();
                                            getActivity().finish();
                                            startActivity(new Intent(getActivity(), Profile.class));
                                        }
                                    })
                                    .show();


                            btn_simpan.setVisibility(View.GONE);
                            BtnEditProfile.setVisibility(View.VISIBLE);
                            nama.setEnabled(false);
                            phone.setEnabled(false);
                            bod.setEnabled(false);
                            alamat.setEnabled(false);
                            ktp.setEnabled(false);


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
    public void onResume() {
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




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }





}