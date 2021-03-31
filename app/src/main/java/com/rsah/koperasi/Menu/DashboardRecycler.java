package com.rsah.koperasi.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsah.koperasi.R;

import static com.rsah.koperasi.Menu.CrawlingCollection.Crawling.reference;

public class DashboardRecycler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_recycler);

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String ref = getIntent().getStringExtra("reference");
        TextView tootTitle = findViewById(R.id.toolbar_title);
        if (ref.equals("sosmed")){
            tootTitle.setText("Social Media");
        } else if (ref.equals("latestsosmed")){
            tootTitle.setText("Latest Post Social Media");
        }


    }
}
