package com.rsah.koperasi.Menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

public class Pengaturan extends AppCompatActivity {


    LinearLayout ubahPwd , keluar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        ubahPwd = findViewById(R.id.ubahPwd) ;
        keluar = findViewById(R.id.btnKeluar) ;

        ubahPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Pengaturan.this, ChangePassword.class));

            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_dialog();

            }
        });



    }



    public void show_dialog(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Pengaturan.this);
        builder1.setMessage("Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SessionManager sessionManager = new SessionManager(Pengaturan.this);
                        sessionManager.logoutUser();
                        Intent intent = new Intent(Pengaturan.this, Login.class);

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
