package com.rsah.koperasi.Menu.Barang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Menu.Pinjaman.DetailPinjamanActivity;
import com.rsah.koperasi.Model.Data.DataPinjaman;
import com.rsah.koperasi.Model.Data.DataRecordPinjaman;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;


public class RecordPinjamanAdapter extends RecyclerView.Adapter<RecordPinjamanAdapter.AdapterHolder>{

    List<DataRecordPinjaman> AllPaymentItemList;
    Context mContext;

    SessionManager sessionManager ;


    public RecordPinjamanAdapter(Context context, List<DataRecordPinjaman> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_pinjaman, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final DataRecordPinjaman responsePaymentMethod = AllPaymentItemList.get(position);

        String reference = responsePaymentMethod.getOrderIdPinjaman();
        String statusDesc = responsePaymentMethod.getStatusDesc();
        String tanggal = responsePaymentMethod.getModifiedDate();
        String desc = responsePaymentMethod.getDeskripsi();


        holder.status.setText(statusDesc);
        holder.desc_.setText(desc);
        holder.date_.setText(tanggal);




    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView date_ , status , desc_ ;
        LinearLayout card ;



        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            date_ = itemView.findViewById(R.id.tvtgl);
            desc_ = itemView.findViewById(R.id.tvdesc);
            status = itemView.findViewById(R.id.tvstatus);

        }
    }






}
