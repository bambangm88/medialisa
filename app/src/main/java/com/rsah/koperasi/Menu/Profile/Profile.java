package com.rsah.koperasi.Menu.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.customfonts.EditTextSFProDisplayRegular;
import com.rsah.koperasi.sessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {


    ProgressDialog pDialog;

    SessionManager sessionManager ;

    private Context mContext;
    private ApiService API;



    EditTextSFProDisplayRegular nama , phone , email , bod , alamat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mContext = this ;
        API = Server.getAPIService();
        sessionManager = new SessionManager(this);

        pDialog = new ProgressDialog(Profile.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        nama = findViewById(R.id.nama) ;
        phone = findViewById(R.id.phone) ;
        email = findViewById(R.id.email) ;
        bod = findViewById(R.id.dateBod) ;
        alamat = findViewById(R.id.alamat) ;


        checkProfile();



    }



    private void checkProfile(){


        pDialog.show();
        Call<ResponseData> call = API.getProfile(sessionManager.getKeyId());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataPeserta() != null) {

                        pDialog.cancel();

                        nama.setText(response.body().getDataPeserta().get(0).getFisrtName());
                        phone.setText(response.body().getDataPeserta().get(0).getMobilePhone());
                        email.setText(response.body().getDataPeserta().get(0).getEmail());
                        bod.setText(response.body().getDataPeserta().get(0).getDateOfBirthDay());
                        alamat.setText(response.body().getDataPeserta().get(0).getAddress());



                    }else{
                        pDialog.cancel();

                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }else{
                    pDialog.cancel();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }




}
