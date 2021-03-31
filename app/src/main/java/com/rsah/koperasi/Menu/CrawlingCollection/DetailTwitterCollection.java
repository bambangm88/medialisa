package com.rsah.koperasi.Menu.CrawlingCollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rsah.koperasi.Adapter.CollectionAdapter;
import com.rsah.koperasi.Adapter.DetailCollectionAdapter;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Webview;
import com.rsah.koperasi.Model.Response.ResponseCollection;
import com.rsah.koperasi.Model.Response.ResponseLogin;
import com.rsah.koperasi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.rsah.koperasi.Menu.CrawlingCollection.Crawling.reference;

public class DetailTwitterCollection extends AppCompatActivity {


    TextView tvUrlAkun , tvtime ,tvCaption , tvUrl , tvlink;

    private RecyclerView rvCollection ;
    private DetailCollectionAdapter Adapter;;
    private List<ResponseCollection.komentar> AllCollectionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_twitter_collection);

        TextView tootTitle = findViewById(R.id.toolbar_title);
        String ref = reference;
        if (ref.equals("twitter")){
            tootTitle.setText("Detail Twitter Collection");
        }else if (ref.equals("facebook")){
            tootTitle.setText("Detail Facebook Collection");
          }else if (ref.equals("youtube")){
            tootTitle.setText("Detail Youtube Collection");
        }else if (ref.equals("ig")){
            tootTitle.setText("Detail Instagram Collection");
        }else if (ref.equals("forum")){
            tootTitle.setText("Detail Forum Collection");
        }else if (ref.equals("Analysis-twitter")){
            tootTitle.setText("Detail Analysis Twitter");
        }else if (ref.equals("Analysis-facebook")){
            tootTitle.setText("Detail Analysis Facebook");
        }else if (ref.equals("Analysis-youtube")){
            tootTitle.setText("Detail Analysis Youtube");
        }else if (ref.equals("Analysis-ig")){
            tootTitle.setText("Detail Analysis Instagram");
        }else if (ref.equals("Analysis-forum")){
            tootTitle.setText("Detail Analysis Forum");
        }
        else if (ref.equals("hastag-twitter")){
            tootTitle.setText("Hastag Twitter");
        }
        else if (ref.equals("hastag-fb")){
            tootTitle.setText("Hastag Facebook");
        }
        else if (ref.equals("hastag-yt")){
            tootTitle.setText("Hastag Youtube");
        }  else if (ref.equals("hastag-ig")){
            tootTitle.setText("Hastag Instagram");
        }
        String link = getIntent().getStringExtra("link");
        String komentar= getIntent().getStringExtra("komentar");
        String urlAkun = getIntent().getStringExtra("urlAkun");
        String time = getIntent().getStringExtra("time");
        String caption = getIntent().getStringExtra("caption");

        setting_recycle_collection() ;

        tvtime = findViewById(R.id.time);
        tvUrlAkun = findViewById(R.id.urlAkun);
        tvCaption= findViewById(R.id.caption);
        tvUrl = findViewById(R.id.url);
        tvlink = findViewById(R.id.link);

        tvUrlAkun.setText(urlAkun);
        tvtime.setText(time);
        tvCaption.setText(caption);
        tvUrl.setText(link);

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tvlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailTwitterCollection.this, Webview.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });


        if (komentar.equals("[]")){
            Toast.makeText(DetailTwitterCollection.this,"Komentar tidak ditemukan",Toast.LENGTH_SHORT).show();
            return;
        }

        AllCollectionList.clear();

        try {
            JSONArray array = new JSONArray(komentar);
            Log.e("TAG", "onCreate: "+array.length() );
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String  username = "" ;
                String twit = "" ;
                if (reference.equals("forum") ){
                    username = object.getString("user");
                    twit = object.getString("deskripsi");
                }else{
                    twit = object.getString("deskripsi");
                    username = object.getString("username");
                }

                String emosion = object.getString("emotion");
                String linkStat = object.getString("link_stat");
                ResponseCollection.komentar cc = new ResponseCollection.komentar();
                cc.setEmotion(emosion);
                cc.setTweet(twit);
                cc.setUsername(username);
                cc.setLink_stat(linkStat);
                AllCollectionList.add(cc);
            }


        }catch (Exception ex){
            Toast.makeText(DetailTwitterCollection.this,ex.getMessage(),Toast.LENGTH_LONG).show();
            return;
        }



        Log.e("TAG", "onCreate: "+AllCollectionList.get(0).getEmotion() );
        rvCollection.setAdapter(new DetailCollectionAdapter(DetailTwitterCollection.this, AllCollectionList));
        Adapter.notifyDataSetChanged();





    }




    private void setting_recycle_collection(){
        rvCollection = findViewById(R.id.rvCollectionDetail);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvCollection.setLayoutManager(mLayoutManager);
        rvCollection.setItemAnimator(new DefaultItemAnimator());
        Adapter = new DetailCollectionAdapter (DetailTwitterCollection.this, AllCollectionList);

    }

}
