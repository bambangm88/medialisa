package com.rsah.koperasi.Menu.Saldo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

public class DetailSaldo extends AppCompatActivity {


    private SessionManager sessionManager ;
    private TextView textname,textcompanycode, textidkop,textsaldo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_saldo);


        sessionManager = new SessionManager(this);


        textname = findViewById(R.id.textname);
        textcompanycode = findViewById(R.id.textcompanycode);
        textidkop = findViewById(R.id.textidkop);
        textsaldo= findViewById(R.id.textsaldo);

        Intent intent = getIntent();
        String saldo = intent.getStringExtra("saldo");

        textname.setText(sessionManager.getUsername());
        textcompanycode.setText(sessionManager.getKeyIdCompany());
        textidkop.setText(sessionManager.getKeyId());
        textsaldo.setText(saldo);













    }






}