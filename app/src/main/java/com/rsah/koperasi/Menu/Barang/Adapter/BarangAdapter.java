package com.rsah.koperasi.Menu.Barang.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.rsah.koperasi.R;
import com.rsah.koperasi.sessionManager.SessionManager;


import java.util.List;

import butterknife.ButterKnife;




public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.AdapterHolder>{

    List<DataBarang> AllPaymentItemList;
    Context mContext;

    SessionManager sessionManager ;


    public BarangAdapter(Context context, List<DataBarang> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final DataBarang responsePaymentMethod = AllPaymentItemList.get(position);

        String title = responsePaymentMethod.getNamaBarang();
        String price = responsePaymentMethod.getHarga();
        String urlImage = responsePaymentMethod.getUrlImage();
        String desc = responsePaymentMethod.getDescBarang();
        String idBarang = responsePaymentMethod.getIDBarang();

        holder.title.setText(title);
        holder.price.setText(Helper.changeToRupiah(price));

        String url = mContext.getString(R.string.baseImageProduct) + urlImage;

        Glide.with(mContext).load(url).into(holder.Image);

        //holder.Image.setBackgroundColor(R.drawable.images);

        holder.btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailBarang.class);
                intent.putExtra("title", title);
                intent.putExtra("price", price);
                intent.putExtra("url", url);
                intent.putExtra("desc", desc);
                intent.putExtra("idBarang", idBarang);
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView title , price ;
        ImageView Image ;
        CardView btnProduct ;



        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            Image = itemView.findViewById(R.id.imgView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            btnProduct = itemView.findViewById(R.id.card_view_product);




        }
    }






}
