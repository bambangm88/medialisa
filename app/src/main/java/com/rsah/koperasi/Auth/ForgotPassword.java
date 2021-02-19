package com.rsah.koperasi.Auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.Data.DataLogin;
import com.rsah.koperasi.Model.Json.JsonLogin;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Response.ResponseLogin;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    TextView btnreg;

    ProgressDialog pDialog;

    Button btn_lupa_pwd ;
    EditText email , password ;

    private Context mContext;
    private ApiService API;

    SessionManager session;

    public static List<DataLogin> AllEntityLogin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        session = new SessionManager(getApplicationContext());

        email = findViewById(R.id.et_email) ;
        btn_lupa_pwd = findViewById(R.id.btn_lupa_pwd);

        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(ForgotPassword.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        btn_lupa_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().equals("")){
                    Toast.makeText(mContext, "Silahkan Isi", Toast.LENGTH_LONG).show();
                    return;
                }

                JsonRegister json = new JsonRegister();
                json.setEmail(email.getText().toString());
                checkEmail(json);

            }
        });


    }

    private void checkEmail(JsonRegister json){

        pDialog.show();
        Call<ResponseLogin> call = API.checkEmail(json);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage() ;
                        String status = response.body().getMetadata().getCode() ;

                        if(status.equals(Constant.ERR_200)){

                            Toast.makeText(mContext, "Berhasil, Silahkan check email", Toast.LENGTH_LONG).show();
                            finish();

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
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }


}
