package com.rsah.koperasi.Menu.Barang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Barang.DetailBarang;
import com.rsah.koperasi.Model.Data.DataBarang;
import com.rsah.koperasi.Model.Data.DataSimpanan;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;


public class SimpananAdapter extends RecyclerView.Adapter<SimpananAdapter.AdapterHolder>{

    List<DataSimpanan> AllPaymentItemList;
    Context mContext;

    SessionManager sessionManager ;


    public SimpananAdapter(Context context, List<DataSimpanan> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_simpanan, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final DataSimpanan responsePaymentMethod = AllPaymentItemList.get(position);

        String tgl_trf = responsePaymentMethod.getTgl_trf();
        String jumlah = responsePaymentMethod.getJumlah();
        String status = responsePaymentMethod.getStatus();


        if (status.equals("00")){
            holder.status_.setText("CONFIRMED");
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.green));
        } else if (status.equals("01")){
            holder.status_.setText("UNCONFIRMED");
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.yellow));
        }else if (status.equals("02")){
             holder.status_.setText("DECLINE");
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }else{
            holder.status_.setText("NOT CONFIRMED");
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }



        holder.jumlah_.setText(Helper.changeToRupiah2(jumlah));
        holder.date_.setText(tgl_trf.replace(".000",""));


    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView date_ , jumlah_ , status_ ;




        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            date_ = itemView.findViewById(R.id.tvdate);
            jumlah_ = itemView.findViewById(R.id.tvjumlah);
            status_ = itemView.findViewById(R.id.tvstatus);

        }
    }






}
