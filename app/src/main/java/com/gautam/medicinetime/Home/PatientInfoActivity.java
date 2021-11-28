package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.gautam.medicinetime.R;

public class PatientInfoActivity extends AppCompatActivity {
    String gender = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Button submit = findViewById(R.id.submit_info_form);
        submit.setOnClickListener(view -> {

            EditText nameEditText = (EditText) findViewById(R.id.full_name);
            String fullName = nameEditText.getText().toString();
            System.out.println(fullName+"ooooooooooooooooo");

            EditText currentWeightEditText = (EditText) findViewById(R.id.weight);
            String weight = currentWeightEditText.getText().toString();

            EditText heightEditText = (EditText) findViewById(R.id.height);
            String height = heightEditText.getText().toString();


            EditText ageEditText = (EditText) findViewById(R.id.age);
            String age = ageEditText.getText().toString();

            EditText phoneEditText = (EditText) findViewById(R.id.Phone);
            String phone = phoneEditText.getText().toString();

            EditText addressEditText = (EditText) findViewById(R.id.address);
            String address = addressEditText.getText().toString();

            RadioButton maleBtn = findViewById(R.id.male);
            RadioButton femaleBtn = findViewById(R.id.female);


            if (maleBtn.isChecked()) {
                gender = "Male";
            } else if (femaleBtn.isChecked()) {
                gender = "Female";
            }
            Intent intent = new Intent(PatientInfoActivity.this, LogIn.class );
            editor.putString("name", fullName);
            System.out.println(fullName+"NAMEEEEEEEEEEEEEEEEEEE");
            editor.putString("age", age);
            System.out.println(age +"AGEEEEEEEEEEEEEE");
            editor.putString("phone", phone);
            editor.putString("address", address);
            editor.putString("weight", weight);
            editor.putString("height", height);
            editor.putString("gender", gender);

            editor.apply();
            startActivity(intent);
        });
    }
}