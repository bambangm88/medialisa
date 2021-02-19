package com.rsah.koperasi.Auth;

import androidx.appcompat.app.AppCompatActivity;

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

import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.Json.JsonLogin;

import com.rsah.koperasi.Model.Data.DataLogin;
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

public class Login extends AppCompatActivity {

    TextView btnreg;

    ProgressDialog pDialog;

    Button btn_login ;
    EditText username , password ;

    private Context mContext;
    private ApiService API;

    private SessionManager session;
    TextView LupaPassword ;
    public static List<DataLogin> AllEntityLogin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnreg = findViewById(R.id.register);


        session = new SessionManager(getApplicationContext());

        username = findViewById(R.id.et_username) ;
        password = findViewById(R.id.et_password);
        LupaPassword = findViewById(R.id.lupaPassword);

        LupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgotPassword.class);
                startActivity(i);
            }
        });


        btn_login = findViewById(R.id.btn_login);

        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(Login.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");


        if (session.isLoggedIn()) {

            Intent i = new Intent(Login.this, MainActivity.class);

            startActivity(i);
            finish();
        }

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,RegisterNew.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pwd = password.getText().toString();

                if (Helper.checkNullInputLogin(user,pwd)){

                    JsonLogin json = new JsonLogin();
                    json.setUsername(user);
                    json.setPassword(pwd);
                    Login(json);

                }else{

                    Toast.makeText(mContext, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    private void Login(JsonLogin json){

        pDialog.show();
        Call<ResponseLogin> call = API.Login(json);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage() ;
                        String status = response.body().getMetadata().getCode() ;

                        if(status.equals(Constant.ERR_200)){

                            pDialog.cancel();

                            AllEntityLogin.addAll(response.body().getResponse().getData()) ;

                            for(DataLogin model : AllEntityLogin) {

                                session.createLoginSession(
                                        model.getFisrtName(),
                                        model.getMemberID(),
                                        model.getEmail(),
                                        model.getMobilePhone(),
                                        model.getImgFace(),
                                        model.getNo_IDCard(),
                                        model.getCompanyName(),
                                        model.getCompanyCode()
                                );

                            }

                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(mContext, "Login Berhasil", Toast.LENGTH_LONG).show();
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
