package com.rsah.koperasi.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rsah.koperasi.Auth.Login;

import com.rsah.koperasi.Menu.CrawlingCollection.TwitterCollection;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import static com.rsah.koperasi.Menu.CrawlingCollection.Crawling.reference;

public class Analysis extends AppCompatActivity {


    LinearLayout ubahPwd , keluar  , version;
    LinearLayout card_service_monitoring, card_twitter_collection,card_forum_collection, card_facebook_collection ,card_ig_collection, card_youtube_collection ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);


        card_twitter_collection = findViewById(R.id.twitter) ;
        card_facebook_collection = findViewById(R.id.facebook_collection) ;
        card_youtube_collection = findViewById(R.id.youtube_collection) ;
        card_ig_collection = findViewById(R.id.instagram_collection) ;
        card_forum_collection = findViewById(R.id.forum_collection) ;



        card_facebook_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Analysis.this, TwitterCollection.class);
                intent.putExtra("ref", "Analysis-facebook");
                reference = "Analysis-facebook" ;
                startActivity(intent);
            }
        });


        card_twitter_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this, TwitterCollection.class);
                intent.putExtra("ref", "Analysis-twitter");
                reference = "Analysis-twitter" ;
                startActivity(intent);

            }
        });

        card_youtube_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this, TwitterCollection.class);
                intent.putExtra("ref", "Analysis-youtube");
                reference = "Analysis-youtube" ;
                startActivity(intent);

            }
        });

        card_ig_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this, TwitterCollection.class);
                intent.putExtra("ref", "Analysis-ig");
                reference = "Analysis-ig" ;
                startActivity(intent);

            }
        });

        card_forum_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this, TwitterCollection.class);
                intent.putExtra("ref", "Analysis-forum");
                reference = "Analysis-forum" ;
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

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Analysis.this);
        builder1.setMessage("Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SessionManager sessionManager = new SessionManager(Analysis.this);
                        sessionManager.logoutUser();
                        Intent intent = new Intent(Analysis.this, Login.class);

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
