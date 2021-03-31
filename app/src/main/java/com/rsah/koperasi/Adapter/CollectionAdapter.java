package com.rsah.koperasi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Menu.CrawlingCollection.DetailTwitterCollection;
import com.rsah.koperasi.Menu.Webview;
import com.rsah.koperasi.Model.Data.DataTrandingWorkSpace;
import com.rsah.koperasi.Model.Response.ResponseCollection;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.AdapterHolder>{

    List<ResponseCollection.data> AllItemList;
    Context mContext;

    SessionManager sessionManager ;


    public CollectionAdapter(Context context, List<ResponseCollection.data> List){
        this.mContext = context;
        AllItemList = List;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final ResponseCollection.data  response = AllItemList.get(position);


       // holder.text_.setText(text);

        holder.judul.setText(response.getNama_akun());
        holder.post.setText(response.getCaption());
        holder.tgl.setText(response.getTanggal_posting());

        String link = response.getPost_url();
        String komentar = response.getData_komentar();
        String urlAkun = response.getNama_akun();
        String time = response.getTanggal_posting();
        String caption = response.getCaption();


        holder.cardDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailTwitterCollection.class);
                intent.putExtra("link", link);
                intent.putExtra("komentar", komentar);
                intent.putExtra("urlAkun", urlAkun);
                intent.putExtra("time", time);
                intent.putExtra("caption", caption);
                mContext.startActivity(intent);
            }
        });

        holder.tvlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Webview.class);
                intent.putExtra("link", link);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return AllItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView judul , post , tgl, tvlink  ;
        LinearLayout card ;
        RelativeLayout cardDetail ;


        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            judul = itemView.findViewById(R.id.judul);
            post = itemView.findViewById(R.id.post);
            tgl = itemView.findViewById(R.id.tgl);
            cardDetail = itemView.findViewById(R.id.card_detail);
            tvlink = itemView.findViewById(R.id.link);

        }
    }






}
