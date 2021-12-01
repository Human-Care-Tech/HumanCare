package com.gautam.medicinetime.Home;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Appointment;
import com.gautam.medicinetime.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorProfileAdapter extends RecyclerView.Adapter<DoctorProfileAdapter.ViewHolder> {


    List<Appointment> doctorsPrivateAppointments = new ArrayList<>();

    public DoctorProfileAdapter(List<Appointment> doctorsAppointments) {
        this.doctorsPrivateAppointments = doctorsAppointments;
    }

    @NonNull
    @Override
    public DoctorProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_list, parent, false);
        return new DoctorProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorProfileAdapter.ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Appointment appointment  = doctorsPrivateAppointments.get(position);
        viewHolder.patientName.setText(appointment.getUser());
        viewHolder.patientAvailableDate.setText(appointment.getDate());
        viewHolder.patientAvailableTime.setText(appointment.getTime());
        viewHolder.patientSymptoms.setText(appointment.getSymptoms());

    }

    @Override
    public int getItemCount() {
        return doctorsPrivateAppointments.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView patientName;
        public TextView patientAvailableDate;
        public TextView patientAvailableTime;
        public TextView patientSymptoms;
        public LinearLayout doctorFrame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = (TextView) itemView.findViewById(R.id.patientNameDoctor);
            patientAvailableDate = (TextView) itemView.findViewById(R.id.datePatientDoctor);
            patientAvailableTime = (TextView) itemView.findViewById(R.id.timePatientDoctor);
            patientSymptoms = (TextView) itemView.findViewById(R.id.patientSymptoms);
            doctorFrame = (LinearLayout) itemView.findViewById(R.id.doctorFrame);

        }

    }
}