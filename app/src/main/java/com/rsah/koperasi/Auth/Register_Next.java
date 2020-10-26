package com.rsah.koperasi.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.Model.ResponseDataKabupaten;
import com.rsah.koperasi.Model.ResponseDataKecamatan;
import com.rsah.koperasi.Model.ResponseDataKelurahan;
import com.rsah.koperasi.Model.ResponseKabupaten;
import com.rsah.koperasi.Model.ResponseKecamatan;
import com.rsah.koperasi.Model.ResponseKelurahan;
import com.rsah.koperasi.Model.ResponseWilayah;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Next extends AppCompatActivity {


    Button btn_next ;

    EditText Alamat ;

    Spinner spProvinsi , spKabupaten , spKecamatan , spKelurahan ;

    LinearLayout layKecamatan , layKelurahan , layKabupaten ;

    ApiService API;

    ProgressDialog pDialog;

    private ArrayList<String> arrayWilayah = new ArrayList<String>();
    private ArrayList<String> arrayIDWilayah = new ArrayList<String>();
    private List<ResponseWilayah> AllWilayahList = new ArrayList<>();



    private ArrayList<String> arrayKabupaten = new ArrayList<String>();
    private ArrayList<String> arrayIDKabupaten = new ArrayList<String>();
    private List<ResponseKabupaten> AllKabupatenList = new ArrayList<>();



    private ArrayList<String> arrayKecamatan = new ArrayList<String>();
    private ArrayList<String> arrayIDKecamatan = new ArrayList<String>();
    private List<ResponseKecamatan> AllKecamatanList = new ArrayList<>();




    private ArrayList<String> arrayKelurahan = new ArrayList<String>();
    private ArrayList<String> arrayIDKelurahan = new ArrayList<String>();
    private List<ResponseKelurahan> AllKelurahanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__next);

        btn_next = findViewById(R.id.btn_next);

        API = Server.getAPIServiceRestApi();

        pDialog = new ProgressDialog(Register_Next.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        Alamat = findViewById(R.id.text_alamat);
        spKelurahan = findViewById(R.id.spKelurahan);
        spKecamatan = findViewById(R.id.spKecamatan);
        spProvinsi = findViewById(R.id.spProvinsi);
        spKabupaten = findViewById(R.id.spKabupaten);


        layKelurahan =findViewById(R.id.layKelurahan);
        layKecamatan =findViewById(R.id.layKecamatan);
        layKabupaten =findViewById(R.id.layKabupaten);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Alm = Alamat.getText().toString();
                String kel = spKelurahan.getSelectedItem().toString();
                String kec = spKecamatan.getSelectedItem().toString();
                String kab = spKabupaten.getSelectedItem().toString();
                String prov = spProvinsi.getSelectedItem().toString();

                if (Alm.isEmpty() || kel.equals("-- Kelurahan --") || kec.equals("-- Kecamatan --") || kab.equals("-- Kabupaten --") || prov.equals("-- Provinsi --")){
                    Toast.makeText(Register_Next.this,"Silahkan Isi",Toast.LENGTH_SHORT).show();
                }else {

                    Register_Next_Simpan reg = new Register_Next_Simpan();

                    reg.TAG_Address = Alm ;
                    reg.TAG_Kelurahan = kel ;
                    reg.TAG_Kecamatan= kec ;
                    reg.TAG_City = kab ;
                    reg.TAG_Province = prov ;

                    startActivity(new Intent(Register_Next.this,Register_Next_Simpan.class));
                }




            }
        });




        getToken();


    }





    private void getToken() {



        API.Token().enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.isSuccessful()){



                    String Token =  response.body().getToken();



                    getProvinsi(Token);


                }else {

                    Toast.makeText(Register_Next.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                Toast.makeText(Register_Next.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getProvinsi(String Token) {




        String url = "https://x.rajaapi.com/MeP7c5ne"+Token+"/m/wilayah/provinsi" ;



        API.Wilayah(url).enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.isSuccessful()){




                    AllWilayahList.addAll(response.body().getData());

                    arrayWilayah.add("-- Provinsi --") ;
                    arrayIDWilayah.add("-- Provinsi --") ;

                    for (ResponseWilayah model : AllWilayahList){

                        arrayWilayah.add(model.getName());
                        arrayIDWilayah.add(model.getId());
                    }





                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register_Next.this, simple_spinner_item,arrayWilayah );
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spProvinsi.setAdapter(spinnerArrayAdapter);



                    layKelurahan.setVisibility(View.GONE);
                    layKecamatan.setVisibility(View.GONE);
                    layKabupaten.setVisibility(View.GONE);

                    btn_next.setEnabled(false);









                    spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String Wilayah = ""+spProvinsi.getSelectedItem() ;
                            int WilayahPosition = position;



                            if(Wilayah.equals("-- Provinsi --") ) {
                                Toast.makeText(Register_Next.this, "Silahkan Pilih Provinsi", Toast.LENGTH_SHORT).show();

                            }
                            else {


                                String IDprov = arrayIDWilayah.get(WilayahPosition);

                                layKabupaten.setVisibility(View.VISIBLE);

                                getKabupaten(Token , IDprov) ;


                                layKelurahan.setVisibility(View.GONE);
                                layKecamatan.setVisibility(View.GONE);

                                btn_next.setEnabled(false);


                                arrayKecamatan.removeAll(arrayKecamatan);
                                arrayKecamatan.clear();
                                arrayIDKecamatan.removeAll(arrayIDKecamatan);
                                arrayIDKecamatan.clear();
                                AllKecamatanList.clear();


                                arrayKelurahan.removeAll(arrayKelurahan);
                                arrayKelurahan.clear();
                                arrayIDKelurahan.removeAll(arrayIDKelurahan);
                                arrayIDKelurahan.clear();
                                AllKelurahanList.clear();


                            }

                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });





















                }else {

                    Toast.makeText(Register_Next.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                Toast.makeText(Register_Next.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getKabupaten(String Token , String ID) {

        String url = "https://x.rajaapi.com/MeP7c5ne"+Token+"/m/wilayah/kabupaten?idpropinsi="+ID ;

        arrayKabupaten.removeAll(arrayKabupaten);
        arrayKabupaten.clear();
        arrayIDKabupaten.removeAll(arrayIDKabupaten);
        arrayIDKabupaten.clear();
        AllKabupatenList.clear();


        API.Kabupaten(url).enqueue(new Callback<ResponseDataKabupaten>() {
            @Override
            public void onResponse(Call<ResponseDataKabupaten> call, Response<ResponseDataKabupaten> response) {

                if (response.isSuccessful()){




                    AllKabupatenList.addAll(response.body().getData());

                    arrayKabupaten.add("-- Kabupaten --") ;
                    arrayIDKabupaten.add("-- Kabupaten --") ;

                    for (ResponseKabupaten model : AllKabupatenList){

                        arrayKabupaten.add(model.getName());
                        arrayIDKabupaten.add(model.getId());
                    }





                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register_Next.this, simple_spinner_item,arrayKabupaten );
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spKabupaten.setAdapter(spinnerArrayAdapter);




                    spKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String Wilayah = ""+spKabupaten.getSelectedItem() ;
                            int WilayahPosition = position;



                            if(Wilayah.equals("-- Kabupaten --") ) {
                                Toast.makeText(Register_Next.this, "Silahkan Pilih Kabupaten", Toast.LENGTH_SHORT).show();

                            }
                            else {


                                String IDprov = arrayIDKabupaten.get(WilayahPosition);

                                layKecamatan.setVisibility(View.VISIBLE);

                                getKecamatan(Token , IDprov); ;


                            }

                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });







                }else {

                    Toast.makeText(Register_Next.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKabupaten> call, Throwable t) {

                Toast.makeText(Register_Next.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getKecamatan(String Token , String ID) {

        String url = "https://x.rajaapi.com/MeP7c5ne"+Token+"/m/wilayah/kecamatan?idkabupaten="+ID ;

        arrayKecamatan.removeAll(arrayKecamatan);
        arrayKecamatan.clear();
        arrayIDKecamatan.removeAll(arrayIDKecamatan);
        arrayIDKecamatan.clear();
        AllKecamatanList.clear();


        API.Kecamatan(url).enqueue(new Callback<ResponseDataKecamatan>() {
            @Override
            public void onResponse(Call<ResponseDataKecamatan> call, Response<ResponseDataKecamatan> response) {

                if (response.isSuccessful()){




                    AllKecamatanList.addAll(response.body().getData());

                    arrayKecamatan.add("-- Kecamatan --") ;
                    arrayIDKecamatan.add("-- Kecamatan --") ;

                    for (ResponseKecamatan model : AllKecamatanList){

                        arrayKecamatan.add(model.getName());
                        arrayIDKecamatan.add(model.getId());
                    }





                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register_Next.this, simple_spinner_item,arrayKecamatan );
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spKecamatan.setAdapter(spinnerArrayAdapter);






                    spKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String Wilayah = ""+spKecamatan.getSelectedItem() ;
                            int WilayahPosition = position;



                            if(Wilayah.equals("-- Kecamatan --") ) {
                                Toast.makeText(Register_Next.this, "Silahkan Pilih Kecamatan", Toast.LENGTH_SHORT).show();

                            }
                            else {


                                String IDprov = arrayIDKecamatan.get(WilayahPosition);

                                layKelurahan.setVisibility(View.VISIBLE);

                                getKelurahan(Token , IDprov); ;


                            }

                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });










                }else {

                    Toast.makeText(Register_Next.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKecamatan> call, Throwable t) {

                Toast.makeText(Register_Next.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





    private void getKelurahan(String Token , String ID) {

        String url = "https://x.rajaapi.com/MeP7c5ne"+Token+"/m/wilayah/kelurahan?idkecamatan="+ID ;

        arrayKelurahan.removeAll(arrayKelurahan);
        arrayKelurahan.clear();
        arrayIDKelurahan.removeAll(arrayIDKelurahan);
        arrayIDKelurahan.clear();
        AllKelurahanList.clear();



        API.Kelurahan(url).enqueue(new Callback<ResponseDataKelurahan>() {
            @Override
            public void onResponse(Call<ResponseDataKelurahan> call, Response<ResponseDataKelurahan> response) {

                if (response.isSuccessful()){




                    AllKelurahanList.addAll(response.body().getData());

                    arrayKelurahan.add("-- Kelurahan --") ;
                    arrayIDKelurahan.add("-- Kelurahan --") ;

                    for (ResponseKelurahan model : AllKelurahanList){

                        arrayKelurahan.add(model.getName());
                        arrayIDKelurahan.add(model.getId());
                    }





                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register_Next.this, simple_spinner_item,arrayKelurahan);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spKelurahan.setAdapter(spinnerArrayAdapter);




                    spKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String Wilayah = ""+spKelurahan.getSelectedItem() ;
                            int WilayahPosition = position;



                            if(Wilayah.equals("-- Kelurahan --") ) {
                                Toast.makeText(Register_Next.this, "Silahkan Pilih Kelurahan", Toast.LENGTH_SHORT).show();

                            }
                            else {

                                btn_next.setEnabled(true);


                            }

                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });






                }else {

                    Toast.makeText(Register_Next.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKelurahan> call, Throwable t) {

                Toast.makeText(Register_Next.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }








}
