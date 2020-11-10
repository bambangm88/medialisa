package com.rsah.koperasi.Menu.Barang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Model.Json.JsonInsertBarang;

import com.rsah.koperasi.Model.Response.ResponseInsertBarang;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarang extends AppCompatActivity {

    private Context mContext;
    private ApiService API;

    private SessionManager session;
    private RelativeLayout rlprogress;

    private TextView tvharga , tvtitle , tvdesc ;
    private ImageView ivproduct ;

    private Button btnAddKrj ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        session = new SessionManager(getApplicationContext());
        rlprogress = findViewById(R.id.rlprogress);
        mContext = this ;
        API = Server.getAPIService();


        tvharga = findViewById(R.id.tvharga);
        tvtitle = findViewById(R.id.tvtitle);
        tvdesc = findViewById(R.id.tvdesc);
        ivproduct = findViewById(R.id.ivproduct);
        btnAddKrj = findViewById(R.id.btnAddKrj);


        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        String desc = getIntent().getStringExtra("desc");
        String url = getIntent().getStringExtra("url");
        String idBarang = getIntent().getStringExtra("idBarang");


        tvharga.setText(price);
        tvtitle.setText(title);
        tvdesc.setText(desc);
        Glide.with(mContext).load(url).into(ivproduct);

        btnAddKrj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idMember  = session.getKeyId();
                JsonInsertBarang json = new JsonInsertBarang();
                json.setIDBarang(idBarang);
                json.setIDMember(idMember);
                requestToKeranjang(json);

            }
        });







    }

    private void requestToKeranjang(JsonInsertBarang json) {

        showProgress(true);
        Call<ResponseInsertBarang> call = API.requestInsertKrj(json);
        call.enqueue(new Callback<ResponseInsertBarang>() {
            @Override
            public void onResponse(Call<ResponseInsertBarang> call, Response<ResponseInsertBarang> response) {
                if(response.isSuccessful()) {
                    showProgress(false);

                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {
                            Toast.makeText(mContext, "Berhasil Memasukan Ke Keranjang", Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                        }

                    }else{
                        showProgress(false);
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }


                }else{
                    showProgress(false);
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertBarang> call, Throwable t) {

                showProgress(false);
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
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




}