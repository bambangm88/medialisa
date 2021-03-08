package com.rsah.koperasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsah.koperasi.Model.Data.DataRecordPinjaman;
import com.rsah.koperasi.Model.Data.DataTrandingWorkSpace;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;


public class TrandingAdapter extends RecyclerView.Adapter<TrandingAdapter.AdapterHolder>{

    List<DataTrandingWorkSpace> AllPaymentItemList;
    Context mContext;

    SessionManager sessionManager ;


    public TrandingAdapter(Context context, List<DataTrandingWorkSpace> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tranding, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final DataTrandingWorkSpace  response = AllPaymentItemList.get(position);

        String text = response.getNama_workspace();
        holder.text_.setText(text);


    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView text_ , status , desc_ ;
        LinearLayout card ;



        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            text_ = itemView.findViewById(R.id.text_tranding);


        }
    }






}
