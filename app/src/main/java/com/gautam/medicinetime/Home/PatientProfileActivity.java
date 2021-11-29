package com.gautam.medicinetime.Home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;

public class PatientProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);


        AuthUser user = Amplify.Auth.getCurrentUser();
        String id = user.getUsername();


//        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
//        String email =sharedPreferences.getString("userEmail","");
//        String userEmail = AuthUser;
//        TextView USER_EMAIL = findViewById(R.id.user_email);
//        USER_EMAIL.setText(email);


        Patient patient = AppDatabase.getInstance(this).userDao().getPatientById(id);

        TextView username = findViewById(R.id.user_name);
        username.setText(id);

        TextView patientName =(TextView) findViewById(R.id.patient_name);
        patientName.setText(patient.fullName);

//        String age = sharedPreferences.getString("age","");
        TextView patientAge =(TextView) findViewById(R.id.patient_age);
        patientAge.setText(patient.age);

//        String address = sharedPreferences.getString("address","");
        TextView patientAddress =(TextView) findViewById(R.id.patient_address);
        patientAddress.setText(patient.address);


//        String phone = sharedPreferences.getString("phone","");
        TextView patientPhone =(TextView) findViewById(R.id.mobile_phone);
        patientPhone.setText(patient.phone);

        TextView gender = findViewById(R.id.patient_gender);
        gender.setText(patient.gender);

        TextView heightAndWeight = findViewById(R.id.height_weight);
        heightAndWeight.setText(patient.height+" cm" + " - " + patient.weight+ " kg" );

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}