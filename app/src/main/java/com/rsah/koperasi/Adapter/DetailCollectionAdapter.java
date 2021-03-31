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
import com.rsah.koperasi.Model.Response.ResponseCollection;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;


public class DetailCollectionAdapter extends RecyclerView.Adapter<DetailCollectionAdapter.AdapterHolder>{

    List<ResponseCollection.komentar> AllItemList;
    Context mContext;

    SessionManager sessionManager ;


    public DetailCollectionAdapter(Context context, List<ResponseCollection.komentar> List){
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
     final ResponseCollection.komentar  response = AllItemList.get(position);


     holder.judul.setText(response.getUsername());
     holder.post.setText(response.getTweet());
     holder.tgl.setText("Emotion: "+response.getEmotion());

     holder.tvlink.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(mContext, Webview.class);
             intent.putExtra("link", response.getLink_stat());
             mContext.startActivity(intent);
         }
     });





    }

    @Override
    public int getItemCount() {
        return AllItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView judul , post , tgl , tvlink ;
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
