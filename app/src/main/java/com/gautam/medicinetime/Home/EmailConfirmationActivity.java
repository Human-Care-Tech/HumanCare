package com.gautam.medicinetime.Home;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.generated.model.User;
import com.gautam.medicinetime.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailConfirmationActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        TextView wrongCode = findViewById(R.id.wrongCode);
        handler = new Handler(Looper.myLooper(), message -> {
            wrongCode.setVisibility(View.VISIBLE);
            return false;
        });
    }



    public void verifyUser(View view) {

        String userName = getIntent().getStringExtra("userName");
        EditText confirmCodeView = findViewById(R.id.confirmCodeText);
        String code = confirmCodeView.getText().toString();


        Amplify.Auth.confirmSignUp(
                userName,
                code,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");

                    Intent intent = new Intent(EmailConfirmationActivity.this, LogIn.class);
                    startActivity(intent);
                },
                error -> {
                    Log.e("AuthQuickstart", error.toString());
                    handler.sendEmptyMessage(1);
                }
        );
    }
}