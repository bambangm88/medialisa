package com.rsah.koperasi.sessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.rsah.koperasi.MainActivity;

import java.util.EnumMap;
import java.util.HashMap;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Sisimangi";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)

    public static final String KEY_USERNAME = "username";
    public static final String KEY_STATUS = "status";

    public static final String KEY_IMAGE_URL = "image_url";

    public static final String KEY_NO_TELP= "notel_";

    public static final String KEY_EMAIL = "email_";
    public static final String KEY_ID_CARD = "idcard_";

    public static final String KEY_ID = "id_";
    public static final String KEY_COMPANY = "companyid_";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     * */
    public void createLoginSession( String username,String ID,String email,String noTelp , String imageUrl,String idCard , String company){ //storelogin
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_USERNAME, username);
        //  editor.putString(KEY_PASSWORD, password);

        editor.putString(KEY_ID, ID);
        //  editor.putString(KEY_PASSWORD, password);

        editor.putString(KEY_EMAIL, email);
        //  editor.putString(KEY_PASSWORD, password);

        editor.putString(KEY_NO_TELP, noTelp);
        //  editor.putString(KEY_PASSWORD, password);

        editor.putString(KEY_IMAGE_URL, imageUrl);
        //  editor.putString(KEY_PASSWORD, password);

        editor.putString(KEY_ID_CARD, idCard);

        editor.putString(KEY_COMPANY, company);

        editor.commit();
    }


    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect to Login Activity

            Intent i = new Intent(_context, MainActivity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    public String getUsername() {
        return pref.getString(KEY_USERNAME, null);
    }

    public String getImageUrl() {
        return pref.getString(KEY_IMAGE_URL, null);
    }


    public String getNoTelp() {
        return pref.getString(KEY_NO_TELP, null);
    }


    public String getKeyEmail() {
        return pref.getString(KEY_EMAIL, null);
    }


    public String getKeyId() {
        return pref.getString(KEY_ID, null);
    }
    public String getKeyIdCard() {
        return pref.getString(KEY_ID_CARD, null);
    }
    public String getKeyIdCompany() {
        return pref.getString(KEY_COMPANY, null);
    }




    public String getStatus(){
        return pref.getString(KEY_STATUS, null);
    }

    /**
     * Get stored session data
     * */
    public HashMap getUserDetails(){

        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        // user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_STATUS, pref.getString(KEY_STATUS, null));
        // return user
        return user;
    }

    /**
     * Hapus Data Session
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}