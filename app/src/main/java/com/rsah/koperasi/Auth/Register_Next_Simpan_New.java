
package com.rsah.koperasi.Auth;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.kalert.KAlertDialog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_ID_CARD;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_PRIBADI;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.Data.DataCompany;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Response.ResponseCompany;
import com.rsah.koperasi.Model.Response.ResponseRegister;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;

public class Register_Next_Simpan_New extends AppCompatActivity {
    int bitmap_size = 60; // range 1 - 100
    Bitmap bitmap, decoded;

    private int GALLERY = 1, CAMERA = 2;

    ImageView fotoIdCard, fotoPribadi;

    Button  btn_register ;

    LinearLayout btn_id_card , btn_pribadi ;

    public static String chooseUpload = "";

    public static String chooseUpload_set = "";

    public static Bitmap bitmapUpload ;

    ApiService API;


    EditText email , notelp  ;

    TextView ttl, unitName , nama ,nik ;

    public String TAG_FisrtName = "";
    public String TAG_Gender = "";
    public String TAG_Religion = "";
    public String TAG_PlaceOfBirthDay = "";
    public String TAG_DateOfBirthDay = "";
    public String TAG_Address = "";
    public String TAG_Kelurahan = "";
    public String TAG_Kecamatan = "";
    public String TAG_City = "";
    public String TAG_Province = "";
    public String TAG_AGAMA = "";
    public String TAG_CompanyCode = "";
    public String TAG_Email = "";
    public String TAG_MobilePhone = "";
    public String TAG_Password = "";
    public String TAG_ImgIDCard = "";
    public String TAG_ImgFace = "";
    public String TAG_IdCard = "";

    EditText  tl  , phone , alamat , pwd ;
    Spinner agama , jk  ;
    EditText et_email , et_pwd ;
    Spinner company ;
    private RelativeLayout rlprogress , rlprogressLoading;

    private ArrayList<String> arrayCompany = new ArrayList<String>();

    private ArrayList<String> arrayJk= new ArrayList<String>();

    private ArrayList<String> arrayAgama= new ArrayList<String>();

    Context mcontext ;

    private List<DataCompany> AllCompanyList = new ArrayList<>();

    public static List<ResponseRegister> AllEntityRegister= new ArrayList<>();

    private ArrayList<String> arrayToken = new ArrayList<String>();
    private List<ResponseData> listToken= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_next_simpan_new);

        API = Server.getAPIService();

        btn_id_card = findViewById(R.id.btn_id_card);
        btn_pribadi = findViewById(R.id.btn_pribadi);
        fotoIdCard = findViewById(R.id.iv_id_card);
        fotoPribadi = findViewById(R.id.iv_pribadi);
        rlprogress = findViewById(R.id.rlprogress);
        btn_register = findViewById(R.id.btn_reg);

        mcontext =this ;


        email = findViewById(R.id.et_email);
        pwd = findViewById(R.id.et_pwd);

        nama = findViewById(R.id.nama);
        jk = findViewById(R.id.jk);
        agama = findViewById(R.id.agama);
        tl = findViewById(R.id.tl);
        ttl = findViewById(R.id.ttl);
        phone = findViewById(R.id.phone);
        alamat = findViewById(R.id.alamat);
        company = findViewById(R.id.spCompany);
        nik = findViewById(R.id.nik);
        unitName = findViewById(R.id.company);


        Bundle bundle=getIntent().getExtras();

        TAG_IdCard = bundle.getString("nik");
        TAG_FisrtName = bundle.getString("name");
        TAG_CompanyCode = bundle.getString("unitCode");
        String cmpName = bundle.getString("unitName");




        nama.setText(TAG_FisrtName);
        nik.setText(TAG_IdCard);
        unitName.setText(cmpName);
        tl.setText(TAG_PlaceOfBirthDay);
        ttl.setText(TAG_DateOfBirthDay);
        phone.setText(TAG_MobilePhone);
        alamat.setText(TAG_Address);


        arrayJk.add("Pilih");
        arrayJk.add("Perempuan");
        arrayJk.add("Laki-Laki");

        ArrayAdapter<String> spinnerJK = new ArrayAdapter<String>(Register_Next_Simpan_New.this, simple_spinner_item,arrayJk );
        spinnerJK .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        jk.setAdapter(spinnerJK);


        arrayAgama.add("Pilih");
        arrayAgama.add("Islam");
        arrayAgama.add("Katolik");
        arrayAgama.add("Protestan");
        arrayAgama.add("Hindu");
        arrayAgama.add("Budha");
        ArrayAdapter<String> spinnerAgama = new ArrayAdapter<String>(Register_Next_Simpan_New.this, simple_spinner_item,arrayAgama );
        spinnerAgama .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        agama.setAdapter(spinnerAgama);


        ttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takePhotoFromCamera();
                Helper.showDateDialog(mcontext,ttl,"yyyy-MM-dd");
            }
        });




        //spCmp = findViewById(R.id.spCompany) ;

        requestMultiplePermissions();

        btn_id_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseUpload = "1" ;

                // showPictureDialog();

                startActivity(new Intent(Register_Next_Simpan_New.this, Foto_ID_CARD.class));
            }
        });


        btn_pribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseUpload = "2" ;
                startActivity(new Intent(Register_Next_Simpan_New.this, Foto_PRIBADI.class));

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mail = email.getText().toString();
                String hp = phone.getText().toString();
                String pw = pwd.getText().toString();

                String _tl = tl.getText().toString();
                String _ttl = ttl.getText().toString();

                String _address = alamat.getText().toString();

                String fotoCard  = getStringImage(imageView2Bitmap(fotoIdCard));
                String fotoProfile = getStringImage(imageView2Bitmap(fotoPribadi));






                if (fotoPribadi.getTag() != "ada" || fotoIdCard.getTag() != "ada"  ){

                    fotoCard = "" ;
                    fotoProfile = "" ;


                }



                if ( jk.getSelectedItem().toString().equals("Pilih") || agama.getSelectedItem().toString().equals("Pilih") || mail.isEmpty() || hp.isEmpty() || pw.isEmpty() || fotoCard.isEmpty() || fotoProfile.isEmpty()){

                    Helper.notifikasi_warning("Silahkan Lengkap dan Pilih Foto",mcontext);


                }else if(pw.length() < 8){

                    Helper.notifikasi_warning("Password Min 8 Karakter",mcontext);

                }
                else{


                    TAG_Email = mail;
                    TAG_DateOfBirthDay = _ttl;
                    TAG_PlaceOfBirthDay = _tl;
                    TAG_Address = _address;



                    TAG_Gender = ""+jk.getSelectedItemPosition();
                    TAG_Religion = ""+agama.getSelectedItemPosition();

                    TAG_MobilePhone = hp;
                    TAG_Password = pw;
                    TAG_ImgIDCard = fotoCard;
                    TAG_ImgFace = fotoProfile;


                    JsonRegister json = new JsonRegister();
                    json.setIdCard(TAG_IdCard);
                    json.setFisrtName(TAG_FisrtName);
                    json.setGender(TAG_Gender);
                    json.setReligion(TAG_Religion);
                    json.setPlaceOfBirthDay(TAG_PlaceOfBirthDay);
                    json.setDateOfBirthDay(TAG_DateOfBirthDay);
                    json.setAddress(TAG_Address);
                    json.setKelurahan(TAG_Kelurahan);
                    json.setKecamatan(TAG_Kecamatan);
                    json.setCity(TAG_City);
                    json.setProvince(TAG_Province);
                    json.setCompanyCode(TAG_CompanyCode);
                    json.setMobilePhone(TAG_MobilePhone);
                    json.setPassword(TAG_Password);
                    json.setImgIDCard(TAG_ImgIDCard);
                    json.setImgFace(TAG_ImgFace);
                    json.setEmail(TAG_Email);
                    DoRegister(json,Register_Next_Simpan_New.this) ;

                }



            }
        });






        //fetchCompany() ;



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


    public void DoRegister(JsonRegister json, Context context){

        showProgress(true);
        Call<ResponseRegister> call = API.Register(json);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if(response.isSuccessful()) {
                    showProgress(false);

                    if (response.body().getMetadata() != null) {

                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();

                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);

                            new KAlertDialog(Register_Next_Simpan_New.this, KAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Notification")
                                    .setContentText("Register Berhasil")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                            finish();
                                            startActivity(new Intent(Register_Next_Simpan_New.this,Login.class));
                                        }
                                    })
                                    .show();


                        }else{
                            showProgress(false);
                           // Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                            Helper.notifikasi_warning(message,context);

                        }

                    }else{
                        showProgress(false);
                        Helper.notifikasi_warning("Terjadi Gangguan",context);
                    }

                }else{
                    showProgress(false);
                    Helper.notifikasi_warning("Terjadi Gangguan",context);
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                showProgress(false);
                Helper.notifikasi_warning(t.getMessage(),context);
            }
        });
    }


    private void fetchCompany() {
        showProgress(true);
        API.fetchCompany().enqueue(new Callback<ResponseCompany>() {
            @Override
            public void onResponse(Call<ResponseCompany> call, Response<ResponseCompany> response) {
                if (response.isSuccessful()){
                    if (response.body().getMetadata() != null) {
                        String message = response.body().getMetadata().getMessage();
                        String status = response.body().getMetadata().getCode();
                        if (status.equals(Constant.ERR_200)) {
                            showProgress(false);
                            AllCompanyList.addAll(response.body().getResponse().getData());
                            arrayCompany.add("-- Perusahaan --") ;
                            for(DataCompany model : AllCompanyList) {
                                if(!arrayCompany.contains(model.getCompany())){
                                    arrayCompany.add(model.getCompany()) ;
                                }
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register_Next_Simpan_New.this, simple_spinner_item,arrayCompany );
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            company.setAdapter(spinnerArrayAdapter);
                        }else {
                            showProgress(false);
                            Toast.makeText(Register_Next_Simpan_New.this,message, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        showProgress(false);
                        Toast.makeText(Register_Next_Simpan_New.this,"Error response data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showProgress(false);
                    Toast.makeText(Register_Next_Simpan_New.this,"Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCompany> call, Throwable t) {
                showProgress(false);
                Toast.makeText(Register_Next_Simpan_New.this,t.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void showProgress(Boolean bool){

        if (bool){
            rlprogress.setVisibility(View.VISIBLE);
        }else {
            rlprogress.setVisibility(View.GONE);
        }
    }





















}

