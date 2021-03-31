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
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.CrawlingCollection.Crawling;
import com.rsah.koperasi.Menu.CrawlingCollection.TwitterCollection;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.Set;

import static com.rsah.koperasi.Menu.CrawlingCollection.Crawling.reference;

public class Setting extends AppCompatActivity {


    LinearLayout ubahPwd , keluar  , version;
    LinearLayout card_logout , cardVersion ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        card_logout = findViewById(R.id.logout) ;
        cardVersion = findViewById(R.id.version) ;
        card_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Helper.Logout(Setting.this);



            }
        });


        cardVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Setting.this, VersionActivity.class);
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

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Setting.this);
        builder1.setMessage("Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SessionManager sessionManager = new SessionManager(Setting.this);
                        sessionManager.logoutUser();
                        Intent intent = new Intent(Setting.this, Login.class);

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
