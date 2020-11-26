package com.rsah.koperasi.Menu.Pinjaman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Menu.Pengaturan;
import com.rsah.koperasi.R;

public class MainPinjaman extends AppCompatActivity {

    LinearLayout pengajuan , histori ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pinjaman);

        pengajuan = findViewById(R.id.pengajuan);
        histori = findViewById(R.id.histori);

        pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPinjaman.this, Pengajuan.class));
            }
        });

        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainPinjaman.this, Pengajuan.class));
            }
        });



    }
}