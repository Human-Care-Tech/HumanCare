package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class DoctorList extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(i);

                        break;

                    case R.id.nav_doctors:
                        fragment = new DoctorFragment();
                        Intent intent = new Intent(getApplicationContext(), DoctorList.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        Intent profile = new Intent(getApplicationContext(), PatientProfileActivity.class);
                        startActivity(profile);
                        break;

                    case R.id.nav_logout:
                        fragment = new LogoutFragment();
                        Intent logout = new Intent(getApplicationContext(), PatientProfileActivity.class);
                        startActivity(logout);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                return true;
            }
        });

        RecyclerView allTasksRecyclerView = findViewById(R.id.recyclerview);
        List<Doctor> docs = GetData(allTasksRecyclerView);


        Log.i("kafawen",docs.toString());
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecyclerView.setAdapter(new DoctorsAdapter(docs));


    }
    private  List<Doctor> GetData( RecyclerView allTaskDataRecyclerView ){
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        List<Doctor> foundDoc=new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Doctor.class),
                response -> {
                    for (Doctor todo : response.getData()) {
                        foundDoc.add(todo);
                        foundDoc.toString();
                        Log.i("MyAmplifyApp", foundDoc.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        return  foundDoc;
    }
public void getData(){


}
}