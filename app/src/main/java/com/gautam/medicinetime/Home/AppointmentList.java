package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.generated.model.Appointment;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppointmentList extends AppCompatActivity {

    SharedPreferences preferences;

    List<Appointment> x= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);




        RecyclerView allTasksRecyclerView = findViewById(R.id.AppoRecyclerViewAbed);
        List<Appointment> appo = GetDataFromAppointment(allTasksRecyclerView);
        Log.i("Finding Appointments", appo.toString());
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        preferences = this.getSharedPreferences("my_pref", MODE_PRIVATE);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        getRecyclerView(appo);

    }

    private void getRecyclerView(List<Appointment> foundAppo) {

        String mSort = preferences.getString("Sort", "ascending");
        if (mSort.equals("ascending")) {
            Collections.sort(foundAppo, Appointment.By_DATE_ASCENDING);
        } else if (mSort.equals("descending")) {
            Collections.sort(foundAppo, Appointment.By_DATE_DESCENDING);
        }
        RecyclerView x= findViewById(R.id.AppoRecyclerViewAbed);

        Log.i("Finding Appointments", foundAppo.toString());
        x.setLayoutManager(new LinearLayoutManager(this));
        x.setAdapter(new AppointmentAdapter(foundAppo));
    }

    private List<Appointment> GetDataFromAppointment(RecyclerView allTaskDataRecyclerView) {
        AuthUser currentUser = Amplify.Auth.getCurrentUser();
        String user = currentUser.getUsername();
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        List<Appointment> foundAppo=new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Appointment.class, Appointment.USER.contains(user)),

                response -> {
                    for (Appointment todo : response.getData()) {
                        foundAppo.add(todo);
                        foundAppo.toString();
                        Log.i("MyAmplifyApp", foundAppo.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    x=foundAppo;
                    handler.sendEmptyMessage(6);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        return foundAppo;
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
                    getRecyclerView(x);
                }
                if (i == 1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("sort", "descending");
                    editor.apply();
                    getRecyclerView(x);
                }
            }
        });
        builder.create().show();
    }


}
