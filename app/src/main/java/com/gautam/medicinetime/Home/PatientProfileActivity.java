package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import android.util.Log;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.squareup.picasso.Picasso;


public class PatientProfileActivity extends AppCompatActivity {

//    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        TextView backToHome = findViewById(R.id.profile_back_btn);
        backToHome.setOnClickListener(view -> {

            Intent backIntent = new Intent(this, DashboardActivity.class);
            startActivity(backIntent);
        });

        AuthUser user = Amplify.Auth.getCurrentUser();
        String id = user.getUsername();

        Patient patient = AppDatabase.getInstance(this).userDao().getPatientById(id);

        TextView username = findViewById(R.id.user_name);
        username.setText(id);

        TextView patientName =(TextView) findViewById(R.id.patient_name);
        patientName.setText(patient.fullName);


        TextView patientAge =(TextView) findViewById(R.id.patient_age);
        patientAge.setText(patient.age);

        TextView patientAddress =(TextView) findViewById(R.id.patient_address);
        patientAddress.setText(patient.address);


        TextView patientPhone =(TextView) findViewById(R.id.mobile_phone);
        patientPhone.setText(patient.phone);

        TextView gender = findViewById(R.id.patient_gender);
        gender.setText(patient.gender);

        TextView heightAndWeight = findViewById(R.id.height_weight);
        heightAndWeight.setText(patient.height+" cm" + " - " + patient.weight+ " kg" );



            Amplify.Storage.getUrl(
                    user.getUsername()+"."+patient.extension,
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully generated: " + result.getUrl());
                        runOnUiThread(() -> {

                                ImageView taskImageDetail = findViewById(R.id.patientImage);
                                System.out.println(result.getUrl());
                                Picasso.get().load(String.valueOf(result.getUrl())).into(taskImageDetail);


                        });
                    },
                    error -> Log.e("MyAmplifyApp", "URL generation failure", error)
            );


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}