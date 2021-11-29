package com.gautam.medicinetime.Home;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;
import com.squareup.picasso.Picasso;

public class PatientProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);


        AuthUser user = Amplify.Auth.getCurrentUser();
        System.out.println(user.getUsername()+"aloooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
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


        System.out.println(patient.extension+"aloooooooooooooooooooooooooooooooooooooooooooooooooooooo");

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