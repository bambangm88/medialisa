package com.rsah.koperasi.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.layout.simple_spinner_item;

import com.rsah.koperasi.Helper.Helper;
import com.rsah.koperasi.Model.ResponseCompany;
import com.rsah.koperasi.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Register extends AppCompatActivity {

    Button btn_next ;
    private DatePickerDialog datePickerDialog;

    private Spinner spinnerAgama , SpinnerJK;
    private ArrayList<String> arrayAgama = new ArrayList<String>();
    private ArrayList<String> arrayJk = new ArrayList<String>();

    private SimpleDateFormat dateFormatter;
    private TimePickerDialog mTimePicker;

    EditText etTgl , etNama , etTempat , et_idCard;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinnerAgama = findViewById(R.id.spAgama);
        SpinnerJK = findViewById(R.id.spJenisKelamin);

        etTgl = findViewById(R.id.et_tgl_lahir);
        etNama = findViewById(R.id.et_Firstname);
        etTempat = findViewById(R.id.et_tpt_lahir);
        et_idCard = findViewById(R.id.et_id_card);

        arrayAgama.add("-- Pilih Agama --") ;
        arrayAgama.add("ISLAM") ;
        arrayAgama.add("KRISTEN") ;
        arrayAgama.add("KATOLIK") ;
        arrayAgama.add("HINDU") ;
        arrayAgama.add("BUDHA") ;
        arrayAgama.add("KONGHUCU") ;

        arrayJk.add("-- Jenis Kelamin --") ;
        arrayJk.add("Laki-laki") ;
        arrayJk.add("Perempuan") ;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Register.this, simple_spinner_item,arrayAgama );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerAgama.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(Register.this, simple_spinner_item,arrayJk );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        SpinnerJK.setAdapter(spinnerArrayAdapter);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        btn_next = findViewById(R.id.btn_next);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Nama = etNama.getText().toString();
                String JK = SpinnerJK.getSelectedItem().toString();
                String Agama = spinnerAgama.getSelectedItem().toString();
                String Tgl = etTgl.getText().toString();
                String Tempat = etTempat.getText().toString();
                String IdCard = et_idCard.getText().toString();

                if (Nama.isEmpty() || JK.equals("-- Jenis Kelamin --") || Agama.equals("-- Pilih Agama --") || IdCard.isEmpty() || Tgl.isEmpty() || Tempat.isEmpty()){

                    Toast.makeText(Register.this,"Silahkan Isi",Toast.LENGTH_SHORT).show();
                }else{

                    Register_Next_Simpan reg = new Register_Next_Simpan();

                    reg.TAG_FisrtName = Nama ;
                    reg.TAG_Gender = Helper.chekJenisKelamin(JK) ;
                    reg.TAG_Religion= Agama ;
                    reg.TAG_DateOfBirthDay = Tgl ;
                    reg.TAG_IdCard = IdCard ;

                    startActivity(new Intent(Register.this,Register_Next.class));
                }




            }
        });


        etTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDateDialog() ;

            }
        });

    }







    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                etTgl.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }









}
