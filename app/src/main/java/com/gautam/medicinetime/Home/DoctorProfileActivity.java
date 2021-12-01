package com.gautam.medicinetime.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Appointment;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorProfileActivity extends AppCompatActivity {


    private static final String TAG = "DoctorProfileActivity";
    SharedPreferences preferences;

    private List<Appointment> appointmentArrayList = new ArrayList<>();
    //    private Doctor Doctors =getUser();
    private Doctor f;
    private RecyclerView doctorRecylerview;

    private DoctorProfileAdapter adapter;
    private final Handler handler = new Handler(Objects.requireNonNull(Looper.myLooper()), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.i(TAG, "Handler Called");
            adapter.notifyDataSetChanged();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        Button signOut=findViewById(R.id.doctorSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                Intent intent = new Intent(DoctorProfileActivity.this,ChoiceActivity.class);
                startActivity(intent);
            }
        });


        TextView x = findViewById(R.id.speciality);
        TextView y = findViewById(R.id.doctorUserName);
//        Doctor d = getDoctor(x);
//        x.setText(d.getSpecialty());
        doctorRecylerview = findViewById(R.id.doctorInfo);
        doctorRecylerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DoctorProfileAdapter(appointmentArrayList);
        doctorRecylerview.setAdapter(adapter);
        preferences = this.getSharedPreferences("my_pref", MODE_PRIVATE);
        getAppointments();
        getDoctor(x);
        getName(y);
    }

    private List<Appointment> getAppointments() {
        AuthUser currentUser = Amplify.Auth.getCurrentUser();
        String user = currentUser.getUsername();



        Amplify.API.query(
                ModelQuery.list(Appointment.class, Appointment.DOCTOR.contains(user)),

                response -> {
                    appointmentArrayList.clear();
                    for (Appointment todo : response.getData()) {
                        appointmentArrayList.add(todo);
                        appointmentArrayList.toString();
                        Log.i("MyAmplifyApp", appointmentArrayList.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    appointmentArrayList = appointmentArrayList;
                    handler.sendEmptyMessage(6);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        return appointmentArrayList;
    }

    public void getDoctor(TextView x) {
        Handler handler1 = new Handler(Objects.requireNonNull(Looper.myLooper()), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Log.i(TAG, "Handler Called");
                x.setText(f.getSpecialty());
                return false;
            }
        });
        AuthUser currentUser = Amplify.Auth.getCurrentUser();
        String user = currentUser.getUsername();
        System.out.println(user.toString()+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        List<Doctor> listD= new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Doctor.class, Doctor.NAME.contains(user)),

                response -> {

                    for (Doctor todo : response.getData()) {
                        listD.add(todo);
                        listD.toString();
                        Log.i("MyAmplifyApp", listD.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    f=listD.get(0);
                    handler1.sendEmptyMessage(6);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


//        return listD.get(0);
    }

    public void getName(TextView y) {
        Handler handler1 = new Handler(Objects.requireNonNull(Looper.myLooper()), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Log.i(TAG, "Handler Called");
                y.setText(f.getName());
                return false;
            }
        });
        AuthUser currentUser = Amplify.Auth.getCurrentUser();
        String user = currentUser.getUsername();
        System.out.println(user.toString()+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        List<Doctor> listD= new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Doctor.class, Doctor.NAME.contains(user)),

                response -> {

                    for (Doctor todo : response.getData()) {
                        listD.add(todo);
                        listD.toString();
                        Log.i("MyAmplifyApp", listD.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    f=listD.get(0);
                    handler1.sendEmptyMessage(6);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


//        return listD.get(0);
    }
}