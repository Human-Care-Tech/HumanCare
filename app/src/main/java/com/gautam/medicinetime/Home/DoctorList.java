package com.gautam.medicinetime.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);



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