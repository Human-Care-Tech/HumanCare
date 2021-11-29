package com.gautam.medicinetime.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PatientInfoActivity extends AppCompatActivity {

    private static final String TAG = "PatientInfoActivity";
    String gender = "";

    static String format = "dd-MM-yyyy";
    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    private static String uploadedFileName = simpleDateFormat.format(new Date());
    private static String uploadedFileExtension = null;
    private static File uploadFile = null;

//    AuthUser user = Amplify.Auth.getCurrentUser();
//    String id = user.getUserId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        // Upload Profile Image

        ImageButton upload = findViewById(R.id.imageButton);
        upload.setOnClickListener(view -> {
            selectFileFromDevice();

        });

        Button submit = findViewById(R.id.submit_info_form);
        submit.setOnClickListener(view -> {

            EditText nameEditText = (EditText) findViewById(R.id.full_name);
            String fullName = nameEditText.getText().toString();
            System.out.println(fullName + "ooooooooooooooooo");

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

            dataStore();
            // store userInfo to database

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String id = sharedPreferences.getString("username", "");
            ///// TODO
            AuthUser user= Amplify.Auth.getCurrentUser();
            System.out.println(id + "aloooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
            String x= uploadedFileExtension.split("/")[1];
            Patient patient = new Patient(user.getUsername(), fullName,x, age, address, phone, height, weight, gender);

            Long patientId = AppDatabase.getInstance(getApplicationContext()).userDao().insertUser(patient);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("id", patientId);
            editor.apply();

            System.out.println("*************************" + "User Id = " + patientId + "**************************");
            System.out.println(patient.getAddress() + "INFOOOOOOOOOOOOOO");
            System.out.println(patient.toString());


            Intent intent = new Intent(PatientInfoActivity.this, DashboardActivity.class);
//            editor.putString("name", fullName);
//            System.out.println(fullName+"NAMEEEEEEEEEEEEEEEEEEE");
//            editor.putString("age", age);
//            System.out.println(age +"AGEEEEEEEEEEEEEE");
//            editor.putString("phone", phone);
//            editor.putString("address", address);
//            editor.putString("weight", weight);
//            editor.putString("height", height);
//            editor.putString("gender", gender);
//
//            editor.apply();
            startActivity(intent);
        });
    }

    public void selectFileFromDevice() {
        Intent upload = new Intent(Intent.ACTION_GET_CONTENT);
        upload.setType("*/*");
        upload = Intent.createChooser(upload, "Choose a File");
        startActivityForResult(upload, 200);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            uploadedFileExtension = getContentResolver().getType(uri);

            Log.i(TAG, "onActivityResult: file extension is " + uploadedFileExtension);
            Log.i(TAG, "onActivityResult: " + data.getData());
            uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFile");

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                FileUtils.copyToFile(inputStream, uploadFile);
            } catch (Exception exception) {
                Log.e(TAG, "File Upload FAILED" + exception.toString());
            }

        }
    }

    private void dataStore() {
//        com.amplifyframework.datastore.generated.model.Patient patient  =  com.amplifyframework.datastore.generated.model
//                .Patient.builder().fullName(fullName).fileName(uploadedFileName +"."+ uploadedFileExtension.split("/")[1]).build();
//
//
//        Amplify.API.mutate(
//                ModelMutation.create(patient), result ->{
//                    Log.i(TAG, "Patient Image Saved");
//                }, error ->{
//                    Log.i(TAG, "Image Not Saved");
//                }
//        );
        AuthUser user = Amplify.Auth.getCurrentUser();
        // uploading file
        Amplify.Storage.uploadFile(
                user.getUsername() + "."+ uploadedFileExtension.split("/")[1],
                uploadFile,
                success -> {
                    Log.i(TAG, "Successfully uploaded:  " + success.getKey());
                },
                error -> {
                    Log.e(TAG, "Upload failed " + error.toString());
                }
        );

        Toast toast = Toast.makeText(getApplicationContext(), "Data Successfully Saved!", Toast.LENGTH_LONG);
        toast.show();
    }
}