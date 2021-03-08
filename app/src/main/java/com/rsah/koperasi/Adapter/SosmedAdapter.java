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

import com.rsah.koperasi.Menu.Barang.Adapter.RecordPinjamanAdapter;
import com.rsah.koperasi.Model.Data.DataRecordPinjaman;
import com.rsah.koperasi.Model.Data.DataTrandingWorkSpace;
import com.rsah.koperasi.Model.Response.ResponseDashboardSosmed;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class SosmedAdapter extends RecyclerView.Adapter<SosmedAdapter.AdapterHolder>{

    ResponseDashboardSosmed AllItemList;
    Context mContext;

    SessionManager sessionManager ;
    private List<ResponseDashboardSosmed.data_dashboard_sosmed> AllSubList = new ArrayList<>();
    private SubSosmedAdapter subAdapter;

    public SosmedAdapter(Context context, ResponseDashboardSosmed List){
        this.mContext = context;
        AllItemList = List;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dasbor_sosmed, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {


        //String text = response.getNama_workspace();
        //holder.text_.setText(text);

        if (position == 0){

            holder.text_.setText("Facebook");
            List<ResponseDashboardSosmed.data_dashboard_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new SubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_persen_fb().size();i++){
                AllsubItem.add(AllItemList.getData_persen_fb().get(i));
            }

            holder.subRV.setAdapter(new SubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();

        }
        if (position == 1){
            holder.text_.setText("Twitter");
            List<ResponseDashboardSosmed.data_dashboard_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new SubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_persen_twitter().size();i++){
                AllsubItem.add(AllItemList.getData_persen_twitter().get(i));
            }

            holder.subRV.setAdapter(new SubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();
        }
        if (position == 2){
            holder.text_.setText("Instagram");
            List<ResponseDashboardSosmed.data_dashboard_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new SubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_persen_ig().size();i++){
                AllsubItem.add(AllItemList.getData_persen_ig().get(i));
            }

            holder.subRV.setAdapter(new SubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();
        }
        if (position == 3){
            holder.text_.setText("Youtube");
            List<ResponseDashboardSosmed.data_dashboard_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new SubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_persen_youtube().size();i++){
                AllsubItem.add(AllItemList.getData_persen_youtube().get(i));
            }

            holder.subRV.setAdapter(new SubSosmedAdapter(mContext, AllsubItem));
            subAdapter.notifyDataSetChanged();
        }
        if (position == 4){
            holder.text_.setText("Forum");
            List<ResponseDashboardSosmed.data_dashboard_sosmed> AllsubItem = new ArrayList<>();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            holder.subRV.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            holder.subRV.setItemAnimator(new DefaultItemAnimator());
            subAdapter = new SubSosmedAdapter(mContext,AllSubList);

            for(int i = 0; i < AllItemList.getData_persen_forum().size();i++){
                AllsubItem.add(AllItemList.getData_persen_forum().get(i));
            }

            holder.subRV.setAdapter(new SubSosmedAdapter(mContext, AllsubItem));
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
