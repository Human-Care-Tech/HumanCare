package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;

public class SignUp extends AppCompatActivity {

//    public void onClickJoin(View view){
//        Intent intent = new Intent(this, EmailConfirmationActivity.class);
//        startActivity(intent);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void registerUser(View view) {

        EditText userNameView = findViewById(R.id.userNameSignUp);
        String userName = userNameView.getText().toString();

        EditText emailView = findViewById(R.id.emailSignUp);
        String email = emailView.getText().toString();

        EditText passwordView = findViewById(R.id.passwordSignUp);
        String password = passwordView.getText().toString();

        /////////////////////////////////////////////////////////////////

        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .build();

        Amplify.Auth.signUp(userName, password, options,
                result ->
                {
                    Log.i("AuthQuickStart", "Result: " + result.toString());
                    Intent intent = new Intent(SignUp.this, PatientInfoActivity.class);
                    intent.putExtra("userName",result.getUser().getUsername());
                    SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",result.getUser().getUsername());
//                    editor.putString("userEmail",email);
                    editor.apply();
                    startActivity(intent);
                },
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );

    }
    public void OnSignUp(View view){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}