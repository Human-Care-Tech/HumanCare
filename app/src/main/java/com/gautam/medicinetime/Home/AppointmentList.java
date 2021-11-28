package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Appointment;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        RecyclerView allTasksRecyclerView = findViewById(R.id.AppoRecyclerViewAbed);
//        List<Appointment> appo = ;
//        System.out.println(appo.toString());
//    for(Appointment x : appo){
//        System.out.println("=====================================");
//
//        System.out.println(x.toString());
//    }

//        Log.i("kafawen",appo.toString());
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecyclerView.setAdapter(new AppointmentAdapter(GetData2(allTasksRecyclerView)));


    }
    private  List<Appointment> GetData2( RecyclerView allTaskDataRecyclerView ){
        AuthUser currentUser= Amplify.Auth.getCurrentUser();
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
                ModelQuery.list(Appointment.class,Appointment.USER.contains(user)),
                response -> {
                    for (Appointment todo : response.getData()) {
                        foundAppo.add(todo);
                        foundAppo.toString();
                        Log.i("MyAmplifyApp", foundAppo.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    handler.sendEmptyMessage(6);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        return  foundAppo;
    }

    }
