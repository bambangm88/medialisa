package com.rsah.koperasi.Menu.Barang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.Data.DataPinjaman;
import com.rsah.koperasi.Model.Data.DataSimpanan;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;


public class PinjamanAdapter extends RecyclerView.Adapter<PinjamanAdapter.AdapterHolder>{

    List<DataPinjaman> AllPaymentItemList;
    Context mContext;

    SessionManager sessionManager ;


    public PinjamanAdapter(Context context, List<DataPinjaman> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pinjaman, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final DataPinjaman responsePaymentMethod = AllPaymentItemList.get(position);

        String tgl_trf = responsePaymentMethod.getTanggalPinjaman();
        String jumlah = responsePaymentMethod.getJumlahPinjaman();
        String status = responsePaymentMethod.getStatus();
        String statusDesc = responsePaymentMethod.getStatusDesc();


        if (status.equals("1")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.yellow));
        } else if (status.equals("2")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.yellow));
        }else if (status.equals("3")){
             holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.yellow));
        }else if (status.equals("4")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.green));
        }else if (status.equals("5")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }else if (status.equals("6")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.green));
        }else if (status.equals("7")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.green));
        }else if (status.equals("8")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.green));
        }else if (status.equals("9")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        else if (status.equals("10")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.green));
        }
        else if (status.equals("11")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        else if (status.equals("12")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        else if (status.equals("13")){
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }else{
            holder.status_.setText(statusDesc);
            holder.status_.setTextColor(mContext.getResources().getColor(R.color.red));
        }



        holder.jumlah_.setText(Helper.changeToRupiah2(jumlah));

        holder.date_.setText(Helper.parseDate(tgl_trf ));


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
