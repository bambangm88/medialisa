package com.rsah.koperasi.Menu.Barang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.Data.DataBarang;
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;


public class AdapterKeranjang extends RecyclerView.Adapter< AdapterKeranjang.AdapterHolder>{

    private List<DataBarang> AllReportList;
    private Context mContext;

    private SessionManager sessionManager ;


    public AdapterKeranjang(Context context, List<DataBarang> reportList){
        this.mContext = context;
        AllReportList = reportList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final DataBarang barang = AllReportList.get(position);

       String title = barang.getNamaBarang();
       String price = barang.getHarga();
       String url = barang.getUrlImage();


       holder.tvtitle.setText(title);
       holder.tvprice.setText(Helper.changeToRupiah(price));
       holder.tvjumlah.setText("1");

       String urlhImg = mContext.getString(R.string.baseImageProduct) + url;

       Glide.with(mContext).load(urlhImg).into(holder.ivimage);

       holder.btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               int jumlah = Integer.parseInt(holder.tvjumlah.getText().toString()) + 1 ;
               holder.tvjumlah.setText(""+jumlah);
           }
       });

        holder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int jumlah = Integer.parseInt(holder.tvjumlah.getText().toString());
                if (jumlah == 1){

                    Toast.makeText(mContext,"Minimal 1",Toast.LENGTH_SHORT).show();
                    return;

                }
                jumlah = jumlah - 1 ;
                holder.tvjumlah.setText(""+jumlah);
            }
        });



    }

    @Override
    public int getItemCount() {
        return AllReportList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{

        TextView tvtitle , tvprice , tvjumlah;
        ImageView ivimage ;
        Button btnAdd ;
        Button btnMin ;


        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            btnAdd  = itemView.findViewById(R.id.btnAdd);
            btnMin  = itemView.findViewById(R.id.btnMin);

            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvprice = itemView.findViewById(R.id.tvprice);
            ivimage = itemView.findViewById(R.id.ivimage);
            tvjumlah = itemView.findViewById(R.id.tvjumlah);

        }
    }






}
