package com.rsah.koperasi.Menu.Pinjaman;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Menu.Barang.Adapter.PinjamanAdapter;
import com.rsah.koperasi.Menu.Barang.Adapter.SimpananAdapter;
import com.rsah.koperasi.Menu.Simpanan.UploadSimpanan;
import com.rsah.koperasi.Model.Data.DataPinjaman;
import com.rsah.koperasi.Model.Data.DataSimpanan;
import com.rsah.koperasi.Model.Json.JsonSimpananSukarela;
import com.rsah.koperasi.Model.Response.ResponsePinjaman;
import com.rsah.koperasi.Model.Response.ResponseSimpanan;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackPinjaman extends AppCompatActivity {



    ProgressDialog pDialog;
    ApiService API;
    private Spinner spinnerMonitor;
    private SessionManager sessionManager ;

    private RecyclerView rvTeam;
    private List<DataPinjaman> AllPinjamanList = new ArrayList<>();
    private PinjamanAdapter Adapter;

    private Context mContext;
    private FloatingActionButton fabAddSimpanan ;
    private RelativeLayout rlprogress ;
    private LinearLayout icon_simpanan_sukarela ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_pinjaman);

        mContext = this ;
        API = Server.getAPIService();
        rlprogress = findViewById(R.id.rlprogress);
        sessionManager = new SessionManager(this);

        rvTeam = findViewById(R.id.rvSimpananSukarela);
        icon_simpanan_sukarela = findViewById(R.id.icon_simpanan_sukarela);

        fabAddSimpanan = findViewById(R.id.btnAdd);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvTeam.setLayoutManager(mLayoutManager);
        rvTeam.setItemAnimator(new DefaultItemAnimator());

        Adapter = new PinjamanAdapter(this, AllPinjamanList);

        pDialog = new ProgressDialog(TrackPinjaman.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        Toolbar toolbar = findViewById(R.id.toolbar_pay);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        icon_simpanan_sukarela.setVisibility(View.INVISIBLE);


        JsonSimpananSukarela json = new JsonSimpananSukarela();
        json.setMemberID(sessionManager.getKeyId());
        getPinjaman(json);

        fabAddSimpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrackPinjaman.this,UploadSimpanan.class));
            }
        });

    }



    private void getPinjaman(JsonSimpananSukarela json){

        //pDialog.show();
        showProgress(true);
        Call<ResponsePinjaman> call = API.getPinjaman(json);
        call.enqueue(new Callback<ResponsePinjaman>() {
            @Override
            public void onResponse(Call<ResponsePinjaman> call, Response<ResponsePinjaman> response) {
                if(response.isSuccessful()) {
                    showProgress(false);
                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {

                            //pDialog.cancel();

                            AllPinjamanList.addAll(response.body().getResponse().getData());
                            rvTeam.setAdapter(new PinjamanAdapter(mContext, AllPinjamanList));
                            Adapter.notifyDataSetChanged();

                        }else{
                            //pDialog.cancel();
                            icon_simpanan_sukarela.setVisibility(View.VISIBLE);
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                        }

                    }else {
                       // pDialog.cancel();
                        showProgress(false);
                        finish();
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                }else{
                   // pDialog.cancel();
                    showProgress(false);
                    finish();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePinjaman> call, Throwable t) {

               // pDialog.cancel();
                showProgress(false);
                finish();
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+"Terjadi Gangguan Pada Server" );
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
