package com.gautam.medicinetime.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Appointment;
import com.amplifyframework.datastore.generated.model.Doctor;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    List<Appointment> allAppointment = new ArrayList<>();

    public AppointmentAdapter(List<Appointment> allTasksData) {
        for(Appointment x : allTasksData ){
            System.out.println("...................................");
            System.out.println(x);
        }

        this.allAppointment = allTasksData; }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_appointment_list, parent, false);
        return new AppointmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Appointment Appointment  = allAppointment.get(position);
        viewHolder.doctorName2.setText(Appointment.getDoctor());
        viewHolder.AppoDate.setText(Appointment.getDate());
        viewHolder.AppoTime.setText(Appointment.getTime());

    }

    @Override
    public int getItemCount() {
        return allAppointment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView doctorName2;
        public TextView AppoDate;
        public TextView AppoTime;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName2 = (TextView) itemView.findViewById(R.id.doctorName2);
            AppoDate = (TextView) itemView.findViewById(R.id.AppoDate);
            AppoTime = (TextView) itemView.findViewById(R.id.AppoTime);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.AppoList_item);

        }

    }
}
