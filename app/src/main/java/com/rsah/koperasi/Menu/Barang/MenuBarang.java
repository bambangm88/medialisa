package com.rsah.koperasi.Menu.Barang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.R;

public class MenuBarang extends AppCompatActivity {


    private LinearLayout btnDaftarBarang , btnKeranjang , btnPesanan ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_barang);


        btnDaftarBarang = findViewById(R.id.btnDaftarBarang);
        btnKeranjang = findViewById(R.id.btnKeranjang);
        btnPesanan = findViewById(R.id.btnPesanan);

        btnDaftarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuBarang.this, Barang.class));
            }
        });


        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuBarang.this, KeranjangActivity.class));
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuBarang.this, PesananActivity.class));
            }
        });


    }
}