package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;
import com.gautam.medicinetime.drugs.medicine.MedicineActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button signInBbutton = findViewById(R.id.signin);
        signInBbutton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        });

        Button signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

        Button signOutButton = findViewById(R.id.signout);
        signOutButton.setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);

        });

        Button goToProfile = findViewById(R.id.profileBtn);
        goToProfile.setOnClickListener(view ->{
            Intent intent= new Intent(this, PatientProfileActivity.class);
            startActivity(intent);
        });

    }


    public void GoToMain (View view){
        Intent intent= new Intent(this, MedicineActivity.class);
        startActivity(intent);

    }
    public void GotoDocList(View view){
        Intent intent= new Intent(this, DoctorList.class);
        startActivity(intent);
    }



}