package com.rsah.koperasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Adapter.LatestSosmedAdapter;
import com.rsah.koperasi.Adapter.SosmedAdapter;
import com.rsah.koperasi.Adapter.TrandingAdapter;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Analysis;
import com.rsah.koperasi.Menu.CrawlingCollection.Crawling;

import com.rsah.koperasi.Model.Data.DataTrandingWorkSpace;
import com.rsah.koperasi.Model.Response.ResponsTrandingWorkSpace;
import com.rsah.koperasi.Model.Response.ResponseDashboardSosmed;
import com.rsah.koperasi.Model.Response.ResponseLatestSosmed;
import com.rsah.koperasi.Model.Response.ResponseLogin;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    TextView txtUser , txtTelp , email , idkop , idkary , txtUcapan ;

    ImageView iv_face ;

    ProgressDialog pDialog;

    SessionManager session ;
    private RelativeLayout rlprogress ;
    CardView cvCrawling, cvAnalysis , cvBarang , cvSaldo , cvPInjaman , cvKeluar , cvSimpanan , cv_shu ;
    LinearLayout card_tranding , card_sosmed , card_latest_sosmed;
    private Context mContext;
    private ApiService API;

    public static String saldo ;

    private RecyclerView rvTranding,rvdasboard_sosmed,rvlatest_sosmed;
    private List<DataTrandingWorkSpace> AllTrandingList = new ArrayList<>();
    private ResponseDashboardSosmed AllSosmedList ;
    private ResponseLatestSosmed AllLatestSosmedList ;
    private TrandingAdapter Adapter;
    private SosmedAdapter Adapter_Sosmed;
    private LatestSosmedAdapter Latest_Adapter_Sosmed;

    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(this);
        rlprogress = findViewById(R.id.rlprogress);
        txtUser = findViewById(R.id.Username);
       // txtEmail = findViewById(R.id.email);

        email= findViewById(R.id.email);

        txtUcapan= findViewById(R.id.txtUcapan);
        cvCrawling= findViewById(R.id.card_crawling);
        cvAnalysis = findViewById(R.id.cv_analysis);

        cvCrawling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Crawling.class));
            }
        });

        cvAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Analysis.class));
            }
        });



        iv_face = findViewById(R.id.iv_face);

        Toolbar toolbar ;
        toolbar = (Toolbar) findViewById(R.id.toolbar_pay);
        setSupportActionBar(toolbar);






        //Helper.cekUcapan(txtUcapan,session.getUsername() );

        Helper.countDown(MainActivity.this);





        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");





        ScrollView scrollView = (ScrollView) findViewById(R.id.mainscroll);
        refreshLayout = findViewById(R.id.swipe_to_refresh_layout);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                // horizontal scroll position
                int scrollX = scrollView.getScrollX();

                // vertical scroll position
                int scrollY = scrollView.getScrollY();

                if (scrollY < 5 ){
                    refreshLayout.setEnabled(true);
                }else{
                    refreshLayout.setEnabled(false);
                }

               // Toast.makeText(SocialServiceMonitoring.this,""+scrollY,Toast.LENGTH_LONG).show();
            }
        });





        //

        refreshLayout.setColorSchemeResources(
                android.R.color.holo_green_dark, android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setting_recycle_tranding();
                        setting_recycle_sosmed();
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

       // checkProfile();

        setting_recycle_tranding();
        setting_recycle_sosmed();
        setting_recycle_latest_sosmed();
    }


    public void show_dialog(){

        new KAlertDialog(mContext, KAlertDialog.WARNING_TYPE)
                .setTitleText("Warning")
                .setContentText("Anda yakin akan keluar ?")
                .setConfirmText("Keluar")
                .setCancelText("Tetap disini")
                .cancelButtonColor(R.color.green, mContext)
                .confirmButtonColor(R.color.red, mContext)
                .setCancelClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        //getDataCustomerFromDBSetToTextview();



                        SessionManager sessionManager = new SessionManager(MainActivity.this);
                        sessionManager.logoutUser();
                        Intent intent = new Intent(MainActivity.this, Login.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();


                    }
                })
                .show();




    }





    private void showProgress(Boolean bool){

        if (bool){
            rlprogress.setVisibility(View.VISIBLE);
        }else {
            rlprogress.setVisibility(View.GONE);
        }
    }






    private void setting_recycle_tranding(){
        card_tranding = findViewById(R.id.card_tranding);
        rvTranding = findViewById(R.id.rvTranding);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvTranding.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvTranding.setItemAnimator(new DefaultItemAnimator());
        Adapter = new TrandingAdapter(this, AllTrandingList);
        request_tranding();
    }

    private void setting_recycle_sosmed(){
        card_sosmed = findViewById(R.id.card_sosmed);
        rvdasboard_sosmed= findViewById(R.id.rvSosmed);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvdasboard_sosmed.setLayoutManager(new GridLayoutManager(this, 2));
        rvdasboard_sosmed.setItemAnimator(new DefaultItemAnimator());
        Adapter_Sosmed = new SosmedAdapter(this, AllSosmedList);
        request_dsb_social_media();
    }


    private void setting_recycle_latest_sosmed(){
        card_latest_sosmed= findViewById(R.id.card_latest_sosmed);
        rvlatest_sosmed= findViewById(R.id.rvLatestSosmed);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvlatest_sosmed.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvlatest_sosmed.setItemAnimator(new DefaultItemAnimator());
        Latest_Adapter_Sosmed = new LatestSosmedAdapter(this, AllLatestSosmedList);
        request_latest_social_media();
    }


    private void request_tranding(){

        AllTrandingList.clear();
        ResponseLogin user = Helper.DecodeFromJsonResponseLogin(session.getDataLogin());
        String token = "Bearer "+user.getAccess_token();
        showProgress(true);
        Call<ResponsTrandingWorkSpace> call = API.dashboardTranding(token);
        call.enqueue(new Callback<ResponsTrandingWorkSpace>() {
            @Override
            public void onResponse(Call<ResponsTrandingWorkSpace> call, Response<ResponsTrandingWorkSpace> response) {
                showProgress(false);
                if(response.isSuccessful()) {
                    if (response.code() == 200){
                        card_tranding.setVisibility(View.VISIBLE);
                        AllTrandingList.addAll(response.body().getData_tranding());
                        rvTranding.setAdapter(new TrandingAdapter(mContext, AllTrandingList));
                        Adapter.notifyDataSetChanged();
                    }

                }

                if (response.code() == 401){

                    new KAlertDialog(mContext, KAlertDialog.ERROR_TYPE)
                            .setTitleText("Session Anda Berakhir")
                            .setContentText("Silahkan Login Kembali")
                            .setConfirmText("OK")
                            .confirmButtonColor(R.color.red_btn_bg_color, mContext)
                            .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                @Override
                                public void onClick(KAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    //getDataCustomerFromDBSetToTextview();
                                    SessionManager sessionManager = new SessionManager(MainActivity.this);
                                    sessionManager.logoutUser();
                                    Intent intent = new Intent(MainActivity.this, Login.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .show();





                }
            }

            @Override
            public void onFailure(Call<ResponsTrandingWorkSpace> call, Throwable t) {
                showProgress(false);
            }
        });
    }


    private void request_dsb_social_media(){

        AllSosmedList = null ;
        ResponseLogin user = Helper.DecodeFromJsonResponseLogin(session.getDataLogin());
        String token = "Bearer "+user.getAccess_token();

        Call<ResponseDashboardSosmed> call = API.dashboardSosmed(token);
        call.enqueue(new Callback<ResponseDashboardSosmed>() {
            @Override
            public void onResponse(Call<ResponseDashboardSosmed> call, Response<ResponseDashboardSosmed> response) {
                if(response.isSuccessful()) {

                    if (response.code() == 200){
                        card_sosmed.setVisibility(View.VISIBLE);
                        AllSosmedList = response.body();
                        rvdasboard_sosmed.setAdapter(new SosmedAdapter(mContext, AllSosmedList));
                        Adapter_Sosmed.notifyDataSetChanged();

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDashboardSosmed> call, Throwable t) {

            }
        });
    }


    private void request_latest_social_media(){

        AllSosmedList = null ;
        ResponseLogin user = Helper.DecodeFromJsonResponseLogin(session.getDataLogin());
        String token = "Bearer "+user.getAccess_token();

        Call<ResponseLatestSosmed> call = API.latestSosmed(token);
        call.enqueue(new Callback<ResponseLatestSosmed>() {
            @Override
            public void onResponse(Call<ResponseLatestSosmed> call, Response<ResponseLatestSosmed> response) {
                if(response.isSuccessful()) {

                    if (response.code() == 200) {
                        card_latest_sosmed.setVisibility(View.VISIBLE);
                        AllLatestSosmedList = response.body();
                        rvlatest_sosmed.setAdapter(new LatestSosmedAdapter(mContext,AllLatestSosmedList));
                        Latest_Adapter_Sosmed.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLatestSosmed> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
       // Helper.countDown(SocialServiceMonitoring.this);
    }
}
