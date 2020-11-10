package com.rsah.koperasi.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Model.Json.JsonUbahPwd;
import com.rsah.koperasi.Model.Response.ResponseUbahPwd;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {



    ProgressDialog pDialog;

    EditText pwd_old, pwd_baru, pwd_confirm ;

    private Context mContext;
    private ApiService API;

    Button btn_ubah ;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        API = Server.getAPIService();
        mContext = this ;

        pDialog = new ProgressDialog(ChangePassword.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        pwd_old = findViewById(R.id.et_pwdOld) ;
        pwd_baru = findViewById(R.id.et_pwdBaru);
        pwd_confirm = findViewById(R.id.et_pwdConfirm);
        btn_ubah = findViewById(R.id.btn_ubah);
        session = new SessionManager(getApplicationContext());

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pwdOld = pwd_old.getText().toString();
                String pwdbaru = pwd_baru.getText().toString();
                String pwdConfirm = pwd_confirm.getText().toString();
                String MID = session.getKeyEmail();

                if (pwdOld.equals("")||pwdbaru.equals("")||pwdConfirm.equals("")){

                    Toast.makeText(mContext,"Silahkan Isi",Toast.LENGTH_SHORT).show();

                }else if (pwdbaru.length() < 8 )   {

                    Toast.makeText(mContext,"Password Minimal 8 Karakter",Toast.LENGTH_SHORT).show();

                } else if (!pwdbaru.equals(pwdConfirm)){

                    Toast.makeText(mContext,"Konfirmasi Passwod Tidak Sama",Toast.LENGTH_SHORT).show();

                } else {

                    JsonUbahPwd json = new JsonUbahPwd();
                    json.setEmail(MID);
                    json.setPass_baru(pwdbaru);
                    json.setPass_old(pwdOld);
                    UbahPwd(json);


                }


            }
        });

    }




    private void UbahPwd(JsonUbahPwd json){

        pDialog.show();
        Call<ResponseUbahPwd> call = API.ubahPwd(json);
        call.enqueue(new Callback<ResponseUbahPwd>() {
            @Override
            public void onResponse(Call<ResponseUbahPwd> call, Response<ResponseUbahPwd> response) {
                if(response.isSuccessful()) {

                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {
                            pDialog.cancel();
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                        }else{
                            pDialog.cancel();
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<ResponseUbahPwd> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }




}
