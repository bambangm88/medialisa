package com.rsah.koperasi.Helper;

import java.security.MessageDigest;
import java.text.NumberFormat;
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






}
