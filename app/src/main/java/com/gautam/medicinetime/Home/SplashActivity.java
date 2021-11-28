package com.gautam.medicinetime.Home;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;

import com.amplifyframework.auth.AuthUser;

import com.amplifyframework.api.graphql.model.ModelMutation;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.gautam.medicinetime.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG ="Splash";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        configureAmplify();

//        testttest();

//        createNotificationChannel();
//
        AuthUser currentUser= Amplify.Auth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentUser==null){
                    Intent i = new Intent(SplashActivity.this, LogIn.class);
                    startActivity(i);
                }
                else {

                    Intent i = new Intent(SplashActivity.this, PatientInfoActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, 2000);
    }
//
    private void configureAmplify() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
//            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.configure(getApplicationContext());

            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize Amplify", error);
        }
    }

    private void testttest(){

            Log.i("MyAmplifyApp", "Initialized Amplify");
            Doctor doc1 = Doctor.builder()
                    .name("doc1").specialty("bons").location("amman-jordan")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(doc1),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );

            Doctor doc2 = Doctor.builder()
                    .name("doc2").specialty("eyes").location("sengafora")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(doc2),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );

            Doctor doc3 = Doctor.builder()
                    .name("doc3").specialty("jazzar").location("India")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(doc3),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
    }

//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(PushListenerService.CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
    }
