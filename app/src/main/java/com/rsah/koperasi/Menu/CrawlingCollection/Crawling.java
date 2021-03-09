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
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

public class Crawling extends AppCompatActivity {


    LinearLayout card_service_monitoring, keluar  , version;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawling);

        card_service_monitoring = findViewById(R.id.card_service_monitoring) ;

        card_service_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Crawling.this, SocialServiceMonitoring.class));

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
