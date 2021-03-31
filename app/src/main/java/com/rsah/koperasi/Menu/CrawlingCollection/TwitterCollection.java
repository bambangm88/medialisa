package com.rsah.koperasi.Menu.CrawlingCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rsah.koperasi.Adapter.CollectionAdapter;
import com.rsah.koperasi.Adapter.TrandingAdapter;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.Data.DataTrandingWorkSpace;
import com.rsah.koperasi.Model.Json.JsonCollection;
import com.rsah.koperasi.Model.Response.ResponseCCServiceMonitoring;
import com.rsah.koperasi.Model.Response.ResponseCollection;
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

public class TwitterCollection extends AppCompatActivity {

    private List<ResponseCollection.data> AllCollectionList = new ArrayList<>();
    private RecyclerView rvCollection;
    private CollectionAdapter Adapter;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_collection);
        ImageView btn_back = findViewById(R.id.btn_back);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setting_recycle_collection();

    }


    private void requestCollectionTwitter(String url, int id_ws) {
        ApiService API = Server.getAPIService();
        SessionManager session = new SessionManager(this);
        ResponseLogin user = Helper.DecodeFromJsonResponseLogin(session.getDataLogin());
        String token = "Bearer " + user.getAccess_token();

        JsonCollection json = new JsonCollection();
        json.setPage_num(1);
        json.setPage_size(10);
        json.setId_ws(id_ws);

        showProgress(true);
        Call<ResponseCollection> call = API.collectionService(token, json, url);
        call.enqueue(new Callback<ResponseCollection>() {
            @Override
            public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                showProgress(false);
                if (response.isSuccessful()) {

                    if (response.code() == 200) {
                        showAvailableLogo(false);
                        AllCollectionList.addAll(response.body().getData());
                        rvCollection.setAdapter(new CollectionAdapter(TwitterCollection.this, AllCollectionList));
                        Adapter.notifyDataSetChanged();
                    }

                }

                if (response.code() == 401) {

                    Helper.sessionBerakhir(TwitterCollection.this);

                }
            }

            @Override
            public void onFailure(Call<ResponseCollection> call, Throwable t) {
                showProgress(false);
            }
        });
    }

    private void requestCollectionFacebook(String url) {
        ApiService API = Server.getAPIService();
        SessionManager session = new SessionManager(this);
        ResponseLogin user = Helper.DecodeFromJsonResponseLogin(session.getDataLogin());
        String token = "Bearer " + user.getAccess_token();

        JsonCollection json = new JsonCollection();
        json.setPage_num(2);
        json.setPage_size(30);

        showProgress(true);
        Call<ResponseCollection> call = API.collectionService(token, json, url);
        call.enqueue(new Callback<ResponseCollection>() {
            @Override
            public void onResponse(Call<ResponseCollection> call, Response<ResponseCollection> response) {
                showProgress(false);
                if (response.isSuccessful()) {

                    if (response.code() == 200) {
                        showAvailableLogo(false);
                        AllCollectionList.addAll(response.body().getData());
                        rvCollection.setAdapter(new CollectionAdapter(TwitterCollection.this, AllCollectionList));
                        Adapter.notifyDataSetChanged();
                    }

                }

                if (response.code() == 401) {

                    Helper.sessionBerakhir(TwitterCollection.this);

                }
            }

            @Override
            public void onFailure(Call<ResponseCollection> call, Throwable t) {
                showProgress(false);
            }
        });
    }

    private void showProgress(Boolean bool) {
        RelativeLayout rlprogress = findViewById(R.id.rlprogress);
        if (bool) {
            rlprogress.setVisibility(View.VISIBLE);
        } else {
            rlprogress.setVisibility(View.GONE);
        }
    }

    private void showAvailableLogo(Boolean bool) {
        ImageView img = findViewById(R.id.unvailable);
        if (bool) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);
        }
    }


    private void setting_recycle_collection() {
        rvCollection = findViewById(R.id.rvCollection);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvCollection.setLayoutManager(mLayoutManager);
        rvCollection.setItemAnimator(new DefaultItemAnimator());
        Adapter = new CollectionAdapter(this, AllCollectionList);
        TextView tootTitle = findViewById(R.id.toolbar_title);
        String ref = getIntent().getStringExtra("ref");
        if (ref.equals("twitter")) {
            tootTitle.setText("Twitter Collection");
            String endpoint = "colection_crawling/services_twitter";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("facebook")) {
            tootTitle.setText("Facebook Collection");
            String endpoint = "colection_crawling/services_facebook";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("youtube")) {
            tootTitle.setText("Youtube Collection");
            String endpoint = "colection_crawling/services_youtube";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("ig")) {
            tootTitle.setText("Instagram Collection");
            String endpoint = "colection_crawling/services_instagram";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("forum")) {
            tootTitle.setText("Forum Collection");
            String endpoint = "colection_crawling/services_forum";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("Analysis-twitter")) {
            tootTitle.setText("Analysis Twitter");
            String endpoint = "analysis/emotion_mining_twitter";
            int id_ws = 13;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("Analysis-facebook")) {
            tootTitle.setText("Analysis Facebook");
            String endpoint = "analysis/emotion_mining_facebook";
            int id_ws = 13;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("Analysis-youtube")) {
            tootTitle.setText("Analysis Youtube");
            String endpoint = "analysis/emotion_mining_youtube";
            int id_ws = 13;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("Analysis-ig")) {
            tootTitle.setText("Analysis Instagram");
            String endpoint = "analysis/emotion_mining_instagram";
            int id_ws = 13;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("Analysis-forum")) {
            tootTitle.setText("Analysis Forum");
            String endpoint = "analysis/emotion_mining_forum";
            int id_ws = 13;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("hastag-twitter")) {
            tootTitle.setText("Hastag Twitter");
            String endpoint = "hastag/twitter";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("hastag-fb")) {
            tootTitle.setText("Hastag Facebook");
            String endpoint = "hastag/facebook";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("hastag-yt")) {
            tootTitle.setText("Hastag Youtube");
            String endpoint = "hastag/youtube";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        } else if (ref.equals("hastag-ig")) {
            tootTitle.setText("Hastag Instagram");
            String endpoint = "hastag/instagram";
            int id_ws = 0;
            requestCollectionTwitter(endpoint, id_ws);
        }



    }
}