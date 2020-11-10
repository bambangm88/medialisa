package com.rsah.koperasi.Menu.Barang;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Menu.Barang.Adapter.AdapterKeranjang;
import com.rsah.koperasi.Menu.Barang.Adapter.AdapterPesanan;
import com.rsah.koperasi.Model.Json.JsonKeranjang;
import com.rsah.koperasi.Model.Json.JsonPesanan;
import com.rsah.koperasi.Model.Response.ResponsePesanan;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.Model.Data.DataBarang;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananActivity extends AppCompatActivity {

    private RecyclerView rvHistoriTrx;
    private List<DataBarang> AllReportList = new ArrayList<>();
    private ApiService API;
    private Context mContext;

    private AdapterPesanan Adapter;

    private LinearLayout btnStartDate, btnEndDate;

    private EditText etStartDate, etEndDate;

    private Button btnCari;

    private RelativeLayout rlprogress;

    private SessionManager sessionManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        mContext = this;
        API = Server.getAPIService();
        sessionManager = new SessionManager(this);



        rlprogress = findViewById(R.id.rlprogress);


        rvHistoriTrx = findViewById(R.id.rvKeranjang);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvHistoriTrx.setLayoutManager(mLayoutManager);
        rvHistoriTrx.setItemAnimator(new DefaultItemAnimator());

        Adapter = new AdapterPesanan(this, AllReportList);


        getPesanan();

    }


    private void getPesanan() {

        showProgress(true);
        JsonPesanan json = new JsonPesanan();
        json.setMemberID(sessionManager.getKeyId());
        Call<ResponsePesanan> call = API.getPesanan(json);
        call.enqueue(new Callback<ResponsePesanan>() {
            @Override
            public void onResponse(Call<ResponsePesanan> call, Response<ResponsePesanan> response) {
                if (response.isSuccessful()) {

                    showProgress(false);


                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {

                            AllReportList.addAll(response.body().getResponse().getData());
                            rvHistoriTrx.setAdapter(new AdapterPesanan(mContext, AllReportList));
                            Adapter.notifyDataSetChanged();

                        }else{
                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }else{
                        showProgress(false);
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }



                } else {
                    showProgress(false);
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }




            }

            @Override
            public void onFailure(Call<ResponsePesanan> call, Throwable t) {
                showProgress(false);
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: " + t.getMessage());
            }
        });
    }

    /*
    private void addPesanan(String memberID) {

        showProgress(true);

        Call<ResponseData> call = API.getKeranjang(memberID);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    showProgress(false);
                    if (response.body().getDataKeranjang() != null) {

                        if (!response.body().getDataKeranjang().isEmpty()) {
                            AllReportList.addAll(response.body().getDataKeranjang());
                            rvHistoriTrx.setAdapter(new AdapterKeranjang(mContext, AllReportList));
                            Adapter.notifyDataSetChanged();

                        } else {

                            Toast.makeText(mContext, "Tidak ada barang di keranjang", Toast.LENGTH_LONG).show();
                            finish();
                        }


                    } else {

                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                } else {
                    showProgress(false);
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                showProgress(false);
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: " + t.getMessage());
            }
        });
    }
    */
    private void showProgress(Boolean bool) {

        if (bool) {
            rlprogress.setVisibility(View.VISIBLE);
        } else {
            rlprogress.setVisibility(View.GONE);
        }
    }

}