package com.rsah.koperasi.Menu.CrawlingCollection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Menu.ChangePassword;
import com.rsah.koperasi.Menu.VersionActivity;
import com.rsah.koperasi.Menu.Webview;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

public class Crawling extends AppCompatActivity {


    LinearLayout card_service_monitoring, card_twitter_collection,card_forum_collection, card_facebook_collection ,card_ig_collection, card_youtube_collection ;
    public static String reference = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawling);

        card_service_monitoring = findViewById(R.id.card_service_monitoring) ;
        card_twitter_collection = findViewById(R.id.card_twitter) ;
        card_facebook_collection = findViewById(R.id.facebook_collection) ;
        card_youtube_collection = findViewById(R.id.youtube_collection) ;
        card_ig_collection = findViewById(R.id.instagram_collection) ;
        card_forum_collection = findViewById(R.id.forum_collection) ;

        card_service_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Crawling.this, SocialServiceMonitoring.class));

            }
        });

        card_facebook_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Crawling.this, TwitterCollection.class);
                intent.putExtra("ref", "facebook");
                reference = "facebook" ;
                startActivity(intent);
            }
        });


        card_twitter_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crawling.this, TwitterCollection.class);
                intent.putExtra("ref", "twitter");
                reference = "twitter" ;
                startActivity(intent);

            }
        });

        card_youtube_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crawling.this, TwitterCollection.class);
                intent.putExtra("ref", "youtube");
                reference = "youtube" ;
                startActivity(intent);

            }
        });

        card_ig_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crawling.this, TwitterCollection.class);
                intent.putExtra("ref", "ig");
                reference = "ig" ;
                startActivity(intent);

            }
        });

        card_forum_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crawling.this, TwitterCollection.class);
                intent.putExtra("ref", "forum");
                reference = "forum" ;
                startActivity(intent);

            }
        });


        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }



    public void show_dialog(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Crawling.this);
        builder1.setMessage("Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SessionManager sessionManager = new SessionManager(Crawling.this);
                        sessionManager.logoutUser();
                        Intent intent = new Intent(Crawling.this, Login.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();


                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }




}
