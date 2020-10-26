package com.rsah.koperasi.Auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import static android.R.layout.simple_spinner_item;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_ID_CARD;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_PRIBADI;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.ResponseCompany;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.Model.ResponseRegister;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Next_Simpan extends AppCompatActivity {
    int bitmap_size = 60; // range 1 - 100
    Bitmap bitmap, decoded;
    ProgressDialog pDialog;
    private int GALLERY = 1, CAMERA = 2;

    ImageView fotoIdCard, fotoPribadi;

    Button  btn_register ;

    LinearLayout btn_id_card , btn_pribadi ;

    public static String chooseUpload = "";

    public static String chooseUpload_set = "";

    public static Bitmap bitmapUpload ;

    Spinner spCmp ;

    ApiService API;


    EditText email , notelp , pwd ;


    public static String TAG_FisrtName = "";
    public static String TAG_Gender = "";
    public static String TAG_Religion = "";
    public static String TAG_PlaceOfBirthDay = "";
    public static String TAG_DateOfBirthDay = "";
    public static String TAG_Address = "";
    public static String TAG_Kelurahan = "";
    public static String TAG_Kecamatan = "";
    public static String TAG_City = "";
    public static String TAG_Province = "";
    public static String TAG_CompanyCode = "";
    public static String TAG_Email = "";
    public static String TAG_MobilePhone = "";
    public static String TAG_Password = "";
    public static String TAG_ImgIDCard = "";
    public static String TAG_ImgFace = "";
    public static String TAG_IdCard = "";


    private ArrayList<String> arrayCompany = new ArrayList<String>();


    private List<ResponseCompany> AllCompanyList = new ArrayList<>();

    public static List<ResponseRegister> AllEntityRegister= new ArrayList<>();

    private ArrayList<String> arrayToken = new ArrayList<String>();
    private List<ResponseData> listToken= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__next__simpan);

        API = Server.getAPIService();

        btn_id_card = findViewById(R.id.btn_id_card);
        btn_pribadi = findViewById(R.id.btn_pribadi);
        fotoIdCard = findViewById(R.id.iv_id_card);
        fotoPribadi = findViewById(R.id.iv_pribadi);

        btn_register = findViewById(R.id.btn_reg);


        email = findViewById(R.id.et_email);
        notelp = findViewById(R.id.et_telp);
        pwd = findViewById(R.id.et_pwd);




        pDialog = new ProgressDialog(Register_Next_Simpan.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        spCmp = findViewById(R.id.spCompany) ;

        requestMultiplePermissions();

        btn_id_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseUpload = "1" ;

               // showPictureDialog();

                startActivity(new Intent(Register_Next_Simpan.this, Foto_ID_CARD.class));
            }
        });


        btn_pribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseUpload = "2" ;
                startActivity(new Intent(Register_Next_Simpan.this, Foto_PRIBADI.class));

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cmp = spCmp.getSelectedItem().toString();
                String mail = email.getText().toString();
                String hp = notelp.getText().toString();
                String pw = pwd.getText().toString();

                String fotoCard  = getStringImage(imageView2Bitmap(fotoIdCard));
                String fotoProfile = getStringImage(imageView2Bitmap(fotoPribadi));






                if (fotoPribadi.getTag() != "ada" || fotoIdCard.getTag() != "ada"  ){

                    fotoCard = "" ;
                    fotoProfile = "" ;


                }





                if (cmp.isEmpty() || cmp.equals("-- Perusahaan --") ||  mail.isEmpty() || hp.isEmpty() || pw.isEmpty() || fotoCard.isEmpty() || fotoProfile.isEmpty()){

                    Toast.makeText(Register_Next_Simpan.this,"Silahkan Isi dan pilih foto",Toast.LENGTH_SHORT).show();

                }else if(pw.length() < 8){

                    Toast.makeText(Register_Next_Simpan.this,"Password Min 8 Karakter",Toast.LENGTH_SHORT).show();

                }
                    else{


                    TAG_CompanyCode = cmp;
                    TAG_Email = mail;
                    TAG_MobilePhone = hp;
                    TAG_Password = pw;
                    TAG_ImgIDCard = fotoCard;
                    TAG_ImgFace = fotoProfile;


                    DoRegister(TAG_IdCard,TAG_FisrtName,
                            TAG_Gender,
                            TAG_Religion,
                            TAG_PlaceOfBirthDay,
                            TAG_DateOfBirthDay,
                            TAG_Address,
                            TAG_Kelurahan,
                            TAG_Kecamatan,
                            TAG_City,
                            TAG_Province,
                            TAG_CompanyCode,
                            TAG_Email,
                            TAG_MobilePhone,
                            TAG_Password,
                            TAG_ImgIDCard,
                            TAG_ImgFace,
                            Register_Next_Simpan.this) ;





                }









            }
        });





        fetchCompany() ;



    }





    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private Bitmap imageView2Bitmap(ImageView view){
        Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
        return bitmap;
    }


    public void DoRegister(String IdCard,String FisrtName ,
                           String Gender ,
                           String Religion ,
                           String PlaceOfBirthDay ,
                           String DateOfBirthDay ,
                           String Address ,
                           String Kelurahan ,
                           String Kecamatan ,
                           String City ,
                           String Province ,
                           String CompanyCode ,
                           String Email ,
                           String MobilePhone ,
                           String Password ,
                           String ImgIDCard ,
                           String ImgFace,
                           Context context){

        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");
        pDialog.show();


        Call<ResponseData> call = API.Register(IdCard,FisrtName ,
                                                            Gender ,
                                                            Religion ,
                                                            PlaceOfBirthDay ,
                                                            DateOfBirthDay ,
                                                            Address ,
                                                            Kelurahan ,
                                                            Kecamatan ,
                                                            City ,
                                                            Province ,
                                                            CompanyCode ,
                                                            Email ,
                                                            MobilePhone ,
                                                            Password ,
                                                            ImgIDCard ,
                                                            ImgFace);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    pDialog.cancel();

                    if(response.body().getDataRegister().isEmpty()) {

                        pDialog.cancel();
                        Toast.makeText(context, "Register Success", Toast.LENGTH_LONG).show();

                        finish();

                        startActivity(new Intent(Register_Next_Simpan.this,Login.class));

                    }else{

                        pDialog.cancel();
                        Toast.makeText(context, "Member Sudah Terdaftar", Toast.LENGTH_LONG).show();

                    }














                }else{
                    pDialog.cancel();
                    Toast.makeText(context, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(context, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }


    private void fetchCompany() {

        pDialog.cancel();

        API.fetchCompany().enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.isSuccessful()){

                    pDialog.cancel();
                    AllCompanyList.addAll(response.body().getDataCompany());



                    arrayCompany.add("-- Perusahaan --") ;

                    for(ResponseCompany model : AllCompanyList) {

                        if(!arrayCompany.contains(model.getCompany())){

                            arrayCompany.add(model.getCompany()) ;

                        }



                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register_Next_Simpan.this, simple_spinner_item,arrayCompany );
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spCmp.setAdapter(spinnerArrayAdapter);






                }else {
                    pDialog.cancel();
                    Toast.makeText(Register_Next_Simpan.this,"Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pDialog.cancel();
                Toast.makeText(Register_Next_Simpan.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(chooseUpload.equals("1")){
            if(chooseUpload_set.equals("1")) {
                fotoIdCard.setImageBitmap(bitmapUpload);
                chooseUpload = "";
                chooseUpload_set ="";
                fotoIdCard.setTag("ada");
            }
        }

        if(chooseUpload.equals("2")){
            if(chooseUpload_set.equals("2")) {
            fotoPribadi.setImageBitmap(bitmapUpload);
            chooseUpload = "";
                chooseUpload_set ="";
                fotoPribadi.setTag("ada");
            }
        }


    }
}
