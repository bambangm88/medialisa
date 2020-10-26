package com.rsah.koperasi.Menu.Barang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.Model.ResponseEntityLogin;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import org.w3c.dom.Text;

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

                requestToKeranjang(idMember,idBarang);
            }
        });







    }

    private void requestToKeranjang(String memberID, String barangID){


        showProgress(true);
        Call<ResponseData> call = API.requestInsertKrj(memberID,barangID);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    showProgress(false);
                    if (response.body().getSuccess() != null) {

                        String success = response.body().getSuccess() ;

                        if (success.equals("0")){
                            Toast.makeText(mContext, "Berhasil Memasukan Ke Keranjang", Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            Toast.makeText(mContext, "Gagal Memasukan Ke Keranjang", Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<ResponseData> call, Throwable t) {

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