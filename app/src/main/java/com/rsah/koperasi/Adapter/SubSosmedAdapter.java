package com.rsah.koperasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Model.Response.ResponseDashboardSosmed;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;


public class SubSosmedAdapter extends RecyclerView.Adapter<SubSosmedAdapter.AdapterHolder>{

    List<ResponseDashboardSosmed.data_dashboard_sosmed> AllItemList;
    Context mContext;

    SessionManager sessionManager ;


    public SubSosmedAdapter(Context context, List<ResponseDashboardSosmed.data_dashboard_sosmed> List){
        this.mContext = context;
        AllItemList = List;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subsosmed, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {


        final ResponseDashboardSosmed.data_dashboard_sosmed dsb_fb = AllItemList.get(position) ;
        holder.text_.setText(dsb_fb.getNama_akun());
        double f = Double.parseDouble(dsb_fb.getPercent_number());
        holder.persen.setText(String.format("%.2f", f)+" %");

    }

    @Override
    public int getItemCount() {
        return AllItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView text_ , persen , desc_ ;
        LinearLayout card ;



        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            text_ = itemView.findViewById(R.id.text_subsosmed);
            persen = itemView.findViewById(R.id.text_persen);


        }
    }






}
