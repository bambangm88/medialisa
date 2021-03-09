package com.rsah.koperasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Model.Response.ResponseDashboardSosmed;
import com.rsah.koperasi.Model.Response.ResponseLatestSosmed;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class LatestSosmedAdapter extends RecyclerView.Adapter<LatestSosmedAdapter.AdapterHolder>{

    ResponseLatestSosmed AllItemList;
    Context mContext;

    SessionManager sessionManager ;
    private List<ResponseLatestSosmed.data_latest_sosmed> AllSubList = new ArrayList<>();
    private LatestSubSosmedAdapter subAdapter;

    public LatestSosmedAdapter(Context context, ResponseLatestSosmed List){
        this.mContext = context;
        AllItemList = List;
        sessionManager = new SessionManager(mContext);
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest_sosmed, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {

        if (position == 0){

            holder.text_.setText("Facebook");
            List<ResponseLatestSosmed.data_latest_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new LatestSubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_latest_fb().size();i++){
                AllsubItem.add(AllItemList.getData_latest_fb().get(i));
            }

            holder.subRV.setAdapter(new LatestSubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();

        }
        if (position == 1){
            holder.text_.setText("Twitter");

            List<ResponseLatestSosmed.data_latest_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new LatestSubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_latest_twitter().size();i++){
                AllsubItem.add(AllItemList.getData_latest_twitter().get(i));
            }

            holder.subRV.setAdapter(new LatestSubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();

        }
        if (position == 2){
            holder.text_.setText("Instagram");

            List<ResponseLatestSosmed.data_latest_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new LatestSubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_latest_ig().size();i++){
                AllsubItem.add(AllItemList.getData_latest_ig().get(i));
            }

            holder.subRV.setAdapter(new LatestSubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();

        }
        if (position == 3){
            holder.text_.setText("Youtube");
            List<ResponseLatestSosmed.data_latest_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new LatestSubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_latest_youtube().size();i++){
                AllsubItem.add(AllItemList.getData_latest_youtube().get(i));
            }

            holder.subRV.setAdapter(new LatestSubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();

        }
        if (position == 4){
            holder.text_.setText("Forum");
            List<ResponseLatestSosmed.data_latest_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new LatestSubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_latest_forum().size();i++){
                AllsubItem.add(AllItemList.getData_latest_forum().get(i));
            }

            holder.subRV.setAdapter(new LatestSubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView text_ , status , desc_ ;
        LinearLayout card ;
        RecyclerView subRV ;


        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            text_ = itemView.findViewById(R.id.judulSosmed);
            subRV = itemView.findViewById(R.id.subrvSosmed);


        }
    }






}
