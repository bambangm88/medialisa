package com.rsah.koperasi.Menu.Barang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.ResponseEntityBarang;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;


public class AdapterPesanan extends RecyclerView.Adapter< AdapterPesanan.AdapterHolder>{

    private List<ResponseEntityBarang> AllReportList;
    private Context mContext;

    private SessionManager sessionManager ;


    public AdapterPesanan(Context context, List<ResponseEntityBarang> reportList){
        this.mContext = context;
        AllReportList = reportList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final ResponseEntityBarang barang = AllReportList.get(position);

       String title = barang.getNamaBarang();
        String price = barang.getHarga();
        String url = barang.getUrlImage();

        String jumlah = barang.getJmlBarang();
        String date = barang.getTglPesanan();
        String status = barang.getApprove();

       holder.tvtitle.setText(title);
       holder.tvprice.setText(Helper.changeToRupiah(price));
       holder.tvjumlah.setText("Jumlah "+jumlah);
       holder.tvdate.setText(date);

        String urlhImg = mContext.getString(R.string.baseImageProduct) + url;

        Glide.with(mContext).load(urlhImg).into(holder.ivimage);

        if (status.equals("00")){
            holder.tvstatus.setText("Approved");
            holder.tvstatus.setTextColor(mContext.getResources().getColor(R.color.green));
        }else if (status.equals("01")){
            holder.tvstatus.setText("Pending");
            holder.tvstatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else if (status.equals("02")){
            holder.tvstatus.setText("Not Approved");
            holder.tvstatus.setTextColor(mContext.getResources().getColor(R.color.red));
        }

    }

    @Override
    public int getItemCount() {
        return AllReportList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{

        TextView tvtitle , tvprice , tvdate , tvjumlah , tvstatus;
        ImageView ivimage ;


        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);



            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvprice = itemView.findViewById(R.id.tvprice);
            ivimage = itemView.findViewById(R.id.ivimage);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvjumlah = itemView.findViewById(R.id.tvjumlah);
            tvstatus = itemView.findViewById(R.id.tvstatus);

        }
    }






}
