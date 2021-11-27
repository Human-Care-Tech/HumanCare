package com.gautam.medicinetime.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gautam.medicinetime.R;
import com.gautam.medicinetime.drugs.medicine.MedicineActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void GoToMain (View view){
        Intent intent= new Intent(this, MedicineActivity.class);
        startActivity(intent);

    }

}