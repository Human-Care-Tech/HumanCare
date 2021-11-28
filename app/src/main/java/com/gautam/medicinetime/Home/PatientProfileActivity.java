package com.gautam.medicinetime.Home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gautam.medicinetime.R;

public class PatientProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String name = sharedPreferences.getString("name","");
        TextView patientName =(TextView) findViewById(R.id.patient_name);
        patientName.setText(name);

        String age = sharedPreferences.getString("age","");
        TextView patientAge =(TextView) findViewById(R.id.patient_age);
        patientAge.setText(age);

        String address = sharedPreferences.getString("address","");
        TextView patientAddress =(TextView) findViewById(R.id.patient_address);
        patientAddress.setText(address);


        String phone = sharedPreferences.getString("phone","");
        TextView patientPhone =(TextView) findViewById(R.id.mobile_phone);
        patientPhone.setText(phone);

    }

}