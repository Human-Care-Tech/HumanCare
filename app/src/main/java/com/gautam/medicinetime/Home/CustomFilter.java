package com.gautam.medicinetime.Home;

import android.widget.Filter;

import com.amplifyframework.datastore.generated.model.Appointment;
import com.amplifyframework.datastore.generated.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter {

    ArrayList<Doctor> filterList= new ArrayList<>();
    DoctorsAdapter adapter;

    public CustomFilter(ArrayList<Doctor> filterList, DoctorsAdapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults filterResults = new FilterResults();

        if (charSequence != null && charSequence.length() > 0) {
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<Doctor> filterDoctor = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getName().toUpperCase().contains(charSequence)) {
                    filterDoctor.add(filterList.get(i));
                }
            }
            filterResults.count = filterDoctor.size();
            filterResults.values = filterDoctor;
        } else {

            filterResults.count = filterList.size();
            filterResults.values = filterList;


        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.allDoctor = (ArrayList<Doctor>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
