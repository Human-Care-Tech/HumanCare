package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;

public class DoctorLogin extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        Button loginDoctor = findViewById(R.id.doctorLoginBtn);
        loginDoctor.setOnClickListener(View -> {

            SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);

            EditText doctorUsername = findViewById(R.id.doctorUsername);
            String userNameDoctor = doctorUsername.getText().toString();
            EditText doctorPassword = findViewById(R.id.doctorPassword);
            String passwordDoctor = doctorPassword.getText().toString();

            Amplify.Auth.signIn(
                    userNameDoctor,
                    passwordDoctor,
                    result -> {
                        Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                        sharedPreferences.edit().putString("userInfo", userNameDoctor).apply();
                        Intent intent = new Intent(DoctorLogin.this, DoctorProfileActivity.class);
                        startActivity(intent);
                    },
                    error -> {
                        Log.e("AuthQuickstart", error.toString());

                        Toast toast = new Toast(this);
                        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
                    }
            );

        });




        Toast toast = Toast.makeText(getApplicationContext(), "User Not Found", Toast.LENGTH_LONG);
        handler = new Handler(Looper.myLooper(), message -> {
            toast.show();
            return false;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }



}