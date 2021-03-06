package com.gautam.medicinetime.Home;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> implements Filterable {


    ArrayList<Doctor> allDoctor, filterList;
    CustomFilter filter;

    public DoctorsAdapter(ArrayList<Doctor> allTasksData) {
        this.allDoctor = allTasksData;
        this.filterList= allDoctor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Doctor Doctor = allDoctor.get(position);
        viewHolder.doctorName.setText(Doctor.getName());
        viewHolder.doctorSpeciality.setText(Doctor.getSpecialty());
        viewHolder.doctorLocation.setText(Doctor.getLocation());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my Adapter", "Element " + viewHolder.getAdapterPosition() + " clicked");
//                Toast.makeText(context, "Submitted!", Toast.LENGTH_SHORT).show();
                String Task1 = viewHolder.doctorName.getText().toString();
                editor.putString("DoctorName", Task1);
                editor.putString("id", Doctor.getId());
                //TODO
//                editor.putString("lat",Doctor.);
                editor.apply();
                Intent gotToStd = new Intent(context, makeAppointment.class);
                context.startActivity(gotToStd);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView doctorName;
        public TextView doctorSpeciality;
        public TextView doctorLocation;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = (TextView) itemView.findViewById(R.id.doctorName);
            doctorSpeciality = (TextView) itemView.findViewById(R.id.doctorSpeciality);
            doctorLocation = (TextView) itemView.findViewById(R.id.doctorLocation);
            doctorLocation = (TextView) itemView.findViewById(R.id.doctorLocation);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.list_item);

        }
    }
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter(filterList, this);
        }
        System.out.println(filter.toString());
        return filter;
    }
    @Override
    public int getItemCount() {
        return allDoctor.size();
    }

}