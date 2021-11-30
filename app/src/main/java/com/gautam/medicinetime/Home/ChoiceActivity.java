package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gautam.medicinetime.R;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        Button doctorButton = findViewById(R.id.doctorChoice);
        doctorButton.setOnClickListener(View -> {

            Intent doctorChoiceIntent = new Intent(ChoiceActivity.this, DoctorLogin.class);
            startActivity(doctorChoiceIntent);

        });

        Button patientButton = findViewById(R.id.patientChoice);
        patientButton.setOnClickListener(View -> {

            Intent patientChoiceIntent = new Intent(ChoiceActivity.this, LogIn.class);
            startActivity(patientChoiceIntent);

        });
    }
}