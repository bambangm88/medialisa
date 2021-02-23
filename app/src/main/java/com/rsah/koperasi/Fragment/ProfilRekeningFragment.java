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
import androidx.fragment.app.FragmentTransaction;
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


public class ProfilRekeningFragment extends Fragment {

    ProgressDialog pDialog;

    SessionManager sessionManager ;

    private Context mContext;
    private ApiService API;
    private ImageView profilePict ;
    private RelativeLayout rlprogress ;

    SwipeRefreshLayout refreshLayout;
    EditTextSFProDisplayRegular bank_rek, nama_rek , no_rek  ;
    Button btn_simpan , BtnEditProfile ;





    public ProfilRekeningFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profil_rek, container, false);



        rlprogress = root.findViewById(R.id.rlprogress);
        mContext = getActivity() ;
        API = Server.getAPIService();
        sessionManager = new SessionManager(mContext);

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        bank_rek = root.findViewById(R.id.bank_rek) ;
        nama_rek = root.findViewById(R.id.nama_rek) ;
        no_rek = root.findViewById(R.id.no_rek) ;
        btn_simpan = root.findViewById(R.id.btn_simpan);

        checkProfile();

        BtnEditProfile = root.findViewById(R.id.btn_edit);
        BtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnEditProfile.setVisibility(View.GONE);
                btn_simpan.setVisibility(View.VISIBLE);
                nama_rek.setEnabled(true);
                bank_rek.setEnabled(true);
                no_rek.setEnabled(true);
                Toast.makeText(mContext, "Silahkan Edit Rekening", Toast.LENGTH_LONG).show();

            }
        });



        refreshLayout = root.findViewById(R.id.swipe_to_refresh_layout);
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
        });


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageString = "" ;

                if (bank_rek.getText().toString().equals("")){
                    bank_rek.setError("REQUIRED");
                    return;
                }

                if (nama_rek.getText().toString().equals("")){
                    nama_rek.setError("REQUIRED");
                    return;
                }

                if (no_rek.getText().toString().equals("")){
                    no_rek.setError("REQUIRED");
                    return;
                }

                JsonRegister json = new JsonRegister();
                json.setNama_rek(nama_rek.getText().toString());
                json.setBank_rek(bank_rek.getText().toString());
                json.setNo_rek(no_rek.getText().toString());
                json.setMemberID(sessionManager.getKeyId());
                json.setType("rekening");
                DoEdit(json,getActivity());


            }
        });




        return root;
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                            bank_rek.setText(response.body().getResponse().getData().get(0).getBank_rek());
                            nama_rek.setText(response.body().getResponse().getData().get(0).getNama_rek());
                            no_rek.setText(response.body().getResponse().getData().get(0).getNo_rek());


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
                                    .setContentText("Rekening Berhasil di Edit")
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
                            bank_rek.setEnabled(false);
                            nama_rek.setEnabled(false);
                            no_rek.setEnabled(false);



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





}