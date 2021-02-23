package com.rsah.koperasi.Menu.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Auth.Register_Next_Simpan_New;
import com.rsah.koperasi.Auth.Upload_Foto.Foto_PRIBADI;
import com.rsah.koperasi.Constant.Constant;
import com.rsah.koperasi.Fragment.MainScanFragment;
import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.MainActivity;
import com.rsah.koperasi.Model.Json.JsonProfile;
import com.rsah.koperasi.Model.Json.JsonRegister;
import com.rsah.koperasi.Model.Json.JsonSaldo;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseProfile;
import com.rsah.koperasi.Model.Response.ResponseRegister;
import com.rsah.koperasi.Model.ResponseData;
import com.rsah.koperasi.R;
import com.rsah.koperasi.api.ApiService;
import com.rsah.koperasi.api.Server;
import com.rsah.koperasi.customfonts.EditTextSFProDisplayRegular;
import com.rsah.koperasi.sessionManager.SessionManager;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.bitmapUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.chooseUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan_New.chooseUpload_set;
import static com.rsah.koperasi.Helper.Helper.imageView2Bitmap;

public class Profile extends AppCompatActivity {


    ProgressDialog pDialog;

    SessionManager sessionManager ;

    private Context mContext;
    private ApiService API;
    private ImageView profilePict ;
    private RelativeLayout rlprogress ;

    SwipeRefreshLayout refreshLayout;
    EditTextSFProDisplayRegular nama , phone , email , bod , alamat ;
    Button btn_simpan , BtnEditProfile ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar_pay);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loadFragment(new MainScanFragment());

    }



    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName());
        // transaction.addToBackStack(null);
        transaction.commit();
    }




    //homeback
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
