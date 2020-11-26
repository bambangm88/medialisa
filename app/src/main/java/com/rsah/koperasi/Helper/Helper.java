package com.rsah.koperasi.Helper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.widget.DatePicker;
import android.widget.TextView;

import com.developer.kalert.KAlertDialog;
import com.rsah.koperasi.Auth.Login;
import com.rsah.koperasi.Auth.Register_Next_Simpan_New;

import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Helper {


    public static boolean checkNullInputLogin(String username , String Password){

        Boolean check = true ;

        if(username.equals("") || Password.equals("")){

            check = false ;

        }

        return check ;


    }


    public static String chekJenisKelamin(String JenisKelamin){

        String jk = "" ;

        if (JenisKelamin.equals("Perempuan")){
            jk = "P";
        }else{
            jk = "L";
        }

        return jk ;

    }


    public static String convertStatus(String code){

        String status = "" ;

        if (code.equals("01")){
            status = "Terdaftar";
        }else if (code.equals("00")){
            status = "Selesai";
        }else{
            status = "Expired";
        }

        return status ;

    }


    public static String convertHariToDaySession(String day){

        String daySession = "" ;

        if (day.contains("Minggu")){
            daySession = "1";
        }

        if (day.contains("Senin")){
            daySession = "2";
        }

        if (day.contains("Selasa")){
            daySession = "3";
        }

        if (day.contains("Rabu")){
            daySession = "4";
        }

        if (day.contains("Kamis")){
            daySession = "5";
        }

        if (day.contains("Jumat")){
            daySession = "6";
        }

        if (day.contains("Sabtu")){
            daySession = "7";
        }


        return daySession ;

    }



    public static String changeToRupiah(String text)
    {
        String rp = text.replace(".00","");
        //conversi currency
        int number = Integer.parseInt(rp);
        String currency = NumberFormat.getNumberInstance(Locale.US).format(number);
        return "Rp "+currency;
    }




    public static String Hash_SHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }



    public static void notifikasi_warning(String message , Context context){
        new KAlertDialog(context, KAlertDialog.WARNING_TYPE)
                .setTitleText("Notification")
                .setContentText(message)
                .setConfirmText("OK")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }


    public static void showDateDialog(Context mContext , final TextView text , String Format){

        DatePickerDialog datePickerDialog;

        final SimpleDateFormat dateFormatter = new SimpleDateFormat(Format, Locale.US);

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(mContext , new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                text.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }




}
