package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Appointment;
import com.gautam.medicinetime.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AppointmentList extends AppCompatActivity {
    BottomNavigationView navigationView;
    private static final String TAG = "AppointmentList";
    SharedPreferences preferences;
    private List<Appointment> appointmentArrayList = new ArrayList<>();
    private RecyclerView allTasksRecyclerView;
    private AppointmentAdapter adapter;
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
        setContentView(R.layout.activity_appointment_list);

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
                        Amplify.Auth.signOut(
                                () -> Log.i("AuthQuickstart", "Signed out successfully"),
                                error -> Log.e("AuthQuickstart", error.toString())
                        );
                        Intent logout = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(logout);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                return true;
            }
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        allTasksRecyclerView = findViewById(R.id.AppoRecyclerViewAbed);
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppointmentAdapter(appointmentArrayList);
        allTasksRecyclerView.setAdapter(adapter);
        preferences = this.getSharedPreferences("my_pref", MODE_PRIVATE);
        getDataFromAppointment();
        sortListData();
    }

    private void sortListData() {
        String mSort = preferences.getString("sort", "ascending");
        if (mSort.equals("ascending")) {
            Collections.sort(appointmentArrayList, Appointment.By_DATE_ASCENDING);
        } else if (mSort.equals("descending")) {
            Collections.sort(appointmentArrayList, Appointment.By_DATE_DESCENDING);
        }
        allTasksRecyclerView = findViewById(R.id.AppoRecyclerViewAbed);
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppointmentAdapter(appointmentArrayList);
        allTasksRecyclerView.setAdapter(adapter);
        Log.i("Finding Appointments", appointmentArrayList.toString());
        adapter.notifyDataSetChanged();
    }

    private List<Appointment> getDataFromAppointment() {
        AuthUser currentUser = Amplify.Auth.getCurrentUser();
        String user = currentUser.getUsername();

        Amplify.API.query(
                ModelQuery.list(Appointment.class, Appointment.USER.contains(user)),

                response -> {
                    appointmentArrayList.clear(); // empties the array list
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menusort, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sortMenu) {
            sortDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDialog() {

        String[] options = {"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("sortBy");
        builder.setIcon(R.drawable.ic_action_sort_background);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("sort", "ascending");
                    editor.apply();
                    sortListData();
                }
                if (i == 1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("sort", "descending");
                    editor.apply();
                    sortListData();
                }
            }
        });
        builder.create().show();
    }

}
