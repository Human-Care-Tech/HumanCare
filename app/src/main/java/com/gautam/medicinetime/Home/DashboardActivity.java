package com.gautam.medicinetime.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.amplifyframework.core.Amplify;
import com.gautam.medicinetime.R;
import com.gautam.medicinetime.drugs.MedicineApp;
import com.gautam.medicinetime.drugs.medicine.MedicineActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * Created by Mohanraj.SK on 6/26/2016.
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener  {

    BottomNavigationView navigationView;


    LinearLayout previousAppointmentVw,scheduleAppointmentVw,medicineTime,profileVw;
    public static final String PREFS_NAME = "MediAuthUser";
    Utils utilsObj = new Utils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.toolbar);
        initializeAll();

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


    }
    public void initializeAll(){
        previousAppointmentVw = (LinearLayout)findViewById(R.id.previous_appointmet);
        scheduleAppointmentVw = (LinearLayout)findViewById(R.id.schedule_appointment);
        profileVw = (LinearLayout)findViewById(R.id.profile_vw);
        medicineTime=findViewById(R.id.medicine_time);
        previousAppointmentVw.setOnClickListener(this);
        scheduleAppointmentVw.setOnClickListener(this);
        profileVw.setOnClickListener(this);
        medicineTime.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_vw :
                Intent intent = new Intent(DashboardActivity.this, PatientProfileActivity.class);
                startActivity(intent);
                break;
            case  R.id.previous_appointmet :
                Intent previousAppointmetIntent = new Intent(DashboardActivity.this, AppointmentList.class);
                startActivity(previousAppointmetIntent);
                break;

            case  R.id.schedule_appointment :

                Intent scheduleAppointmentIntent = new Intent(DashboardActivity.this, DoctorList.class);
                startActivity(scheduleAppointmentIntent);

                break;

            case R.id.medicine_time:
                Intent intent2 = new Intent(DashboardActivity.this, MedicineActivity.class);
                startActivity(intent2);
                break;

        }

    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater Inflater = getMenuInflater();
//        Inflater.inflate(R.menu.menu_login, menu);
//        return true;
//    }
    public void switchActivity(Intent intent){

        startActivity(intent);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.logout) {
//            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//            SharedPreferences.Editor editor = settings.edit();
//            editor.putBoolean("isAlreadyLoggedIn", false);
//            editor.putString("AutoLog","no");
//            // editor.remove("username");
//
//
//            editor.commit();
//
//            System.out.println("Going to stop the service");
//            //  Intent intent= new Intent(this, MyService.class);
////            this.stopService(intent);
//
//            finish();
//            Intent loginIntent = new Intent(this,LoginActivity.class);
//            startActivity(loginIntent);
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    public void SignOut(View view){
//        Amplify.Auth.signOut(
//                () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Intent intent = new Intent(this, LogIn.class);
//        startActivity(intent);
//    }

}