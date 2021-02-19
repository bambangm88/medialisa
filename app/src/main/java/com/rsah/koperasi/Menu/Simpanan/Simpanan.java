package com.rsah.koperasi.Menu.Simpanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.R;

public class Simpanan extends AppCompatActivity {


    private LinearLayout simpananSukarela ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpanan);


        simpananSukarela = findViewById(R.id.simpananSukarela);

        simpananSukarela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Simpanan.this, listSimpanan.class));
            }
        });

    }
}