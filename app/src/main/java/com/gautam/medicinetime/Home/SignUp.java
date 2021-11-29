package com.gautam.medicinetime.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;

public class SignUp extends AppCompatActivity {

    public void onClickJoin(View view){
        Intent intent = new Intent(this, EmailConfirmationActivity.class);
        startActivity(intent);
    }
    public void onLoginClick(View view){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }


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
                    Intent intent = new Intent(SignUp.this,LogIn.class);
                    intent.putExtra("userName",result.getUser().getUsername());
                    startActivity(intent);
                },
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );

    }
}