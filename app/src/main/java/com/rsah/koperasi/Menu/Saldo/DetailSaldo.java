package com.rsah.koperasi.Menu.Saldo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.Json.JsonSaldo;
import com.rsah.koperasi.Model.Response.ResponseSaldo;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSaldo extends AppCompatActivity {
    private ApiService API;
    ProgressDialog pDialog;
    private SessionManager sessionManager ;
    private Context mContext;
    private TextView textname,textcompanycode, textidkop,textsaldo , textsaldo_sukarela , textsaldo_wajib ;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_saldo);


        sessionManager = new SessionManager(this);
        API = Server.getAPIService();
        mContext = this ;
        pDialog = new ProgressDialog(DetailSaldo.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");


        textname = findViewById(R.id.textname);
        textcompanycode = findViewById(R.id.textcompanycode);
        textidkop = findViewById(R.id.textidkop);
        textsaldo= findViewById(R.id.textsaldo);
        textsaldo_sukarela= findViewById(R.id.textsaldo_sukarela);
        textsaldo_wajib= findViewById(R.id.textsaldo_wajib);



        textname.setText(sessionManager.getUsername());
        textcompanycode.setText(sessionManager.getKeyIdCompany());
        textidkop.setText(sessionManager.getKeyId());

        checkSaldo();

        refreshLayout = findViewById(R.id.swipe_to_refresh_layout);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_green_dark, android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkSaldo();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });


    }



    private void checkSaldo(){

        JsonSaldo json = new JsonSaldo();
        json.setMemberID(sessionManager.getKeyId());
        pDialog.show();
        Call<ResponseSaldo> call = API.getSaldo(json);
        call.enqueue(new Callback<ResponseSaldo>() {
            @Override
            public void onResponse(Call<ResponseSaldo> call, Response<ResponseSaldo> response) {
                if(response.isSuccessful()) {
                    if (response.body().getMetadata() != null) {


                        String message = response.body().getMetadata().getMessage() ;
                        String status = response.body().getMetadata().getCode() ;

                        if(status.equals(Constant.ERR_200)){

                            pDialog.cancel();
                            String sal = response.body().getResponse().getData().get(0).getSaldo();
                            String sal_sukarela = response.body().getResponse().getData().get(0).getSaldo_sukarela();
                            String sal_wajib = response.body().getResponse().getData().get(0).getSaldo_wajib();
                            if (sal == null || sal.equals("null")){
                               // saldo = "Rp 0";
                                textsaldo.setText("Rp 0");
                                textsaldo_sukarela.setText("Rp 0");
                                textsaldo_wajib.setText("Rp 0");
                                Toast.makeText(mContext, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                            }else {

                                textsaldo.setText(sal);
                                textsaldo_sukarela.setText(sal_sukarela);
                                textsaldo_wajib.setText(sal_wajib);
                            }
                            //saldo.setText("Rp "+response.body().getDataSaldo().get(0).getSaldo());

                        }else{
                            pDialog.cancel();
                            //Toast.makeText(mContext, "Email / Password salah", Toast.LENGTH_LONG).show();

                            textsaldo.setText("Rp 0");
                            textsaldo_sukarela.setText("Rp 0");
                            textsaldo_wajib.setText("Rp 0");
                        }

                    }else{
                        pDialog.cancel();
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                        textsaldo.setText("Rp 0");
                        textsaldo_sukarela.setText("Rp 0");
                        textsaldo_wajib.setText("Rp 0");
                    }

                }else{
                    pDialog.cancel();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    textsaldo.setText("Rp 0");
                    textsaldo_sukarela.setText("Rp 0");
                    textsaldo_wajib.setText("Rp 0");
                }
            }

            @Override
            public void onFailure(Call<ResponseSaldo> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }




}