package com.rsah.koperasi.Auth.Upload_Foto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rsah.koperasi.Auth.Register_Next_Simpan;
import com.rsah.koperasi.R;

import java.io.IOException;

import static com.rsah.koperasi.Auth.Register_Next_Simpan.bitmapUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan.chooseUpload;
import static com.rsah.koperasi.Auth.Register_Next_Simpan.chooseUpload_set;

public class Foto_ID_CARD extends AppCompatActivity {

    int bitmap_size = 60; // range 1 - 100
    Bitmap bitmap, decoded;
    ProgressDialog pDialog;
    private int GALLERY = 1, CAMERA = 2;

    Button btn_upload ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto__id__card);

        btn_upload = findViewById(R.id.btn_upload);



        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPictureDialog();

            }
        });


    }


    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select berkas from gallery",
                "Capture berkas from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }



    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {



                     Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                     bitmapUpload = bitmap ;
                     chooseUpload_set = "1";

                    finish();


                     //fotoIdCard.setImageBitmap(bitmap);


//                    if(chooseUpload.equals("1")){
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                        fotoIdCard.setImageBitmap(bitmap);
//                    }
//
//                    if(chooseUpload.equals("2")){
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                        fotoPribadi.setImageBitmap(bitmap);
//                    }



                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Foto_ID_CARD.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {



                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                bitmapUpload = thumbnail ;
                chooseUpload_set = "1";

               // fotoIdCard.setImageBitmap(thumbnail);
                finish();



            // imageView2Bitmap(berkas1);

            // Toast.makeText(UploadBerkas.this, "encode"+getStringImage(imageView2Bitmap(berkas1)), Toast.LENGTH_SHORT).show();
        }
    }

}
