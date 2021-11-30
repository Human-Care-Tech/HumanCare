package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;


import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import android.view.MenuItem;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;


import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DoctorList extends AppCompatActivity {
    BottomNavigationView navigationView;

    DoctorsAdapter doctorsAdapter ;

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(myToolbar);


        RecyclerView allTasksRecyclerView = findViewById(R.id.recyclerview);
        ArrayList<Doctor> docs = GetData(allTasksRecyclerView);
        Log.i("kafawen", docs.toString());
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorsAdapter= new DoctorsAdapter(docs);
        allTasksRecyclerView.setAdapter(doctorsAdapter);
    }



    private ArrayList<Doctor> GetData(RecyclerView allTaskDataRecyclerView) {
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        ArrayList<Doctor> foundDoc = new ArrayList<>();
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

        return foundDoc;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);

        MenuItem item = menu.findItem(R.id.searchmenu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doctorsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doctorsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }


}