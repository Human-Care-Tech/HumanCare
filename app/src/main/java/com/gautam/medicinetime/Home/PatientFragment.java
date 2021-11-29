package com.gautam.medicinetime.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gautam.medicinetime.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "fullName";
    private static final String ARG_PARAM2 = "age";
    private static final String ARG_PARAM3 = "address";
    private static final String ARG_PARAM4 = "phone";

    // TODO: Rename and change types of parameters
    private String mfullName;
    private String mage;
    private String maddress;
    private String mphone;

    public PatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mfullName Parameter 1.
     * @param mage Parameter 2.
     * @param maddress Parameter 3.
     * @param mphone Parameter 4.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientFragment newInstance(String mfullName, Number mage, String maddress, Number mphone) {
        PatientFragment fragment = new PatientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mfullName);
        args.putString(ARG_PARAM2, String.valueOf(mage));
        args.putString(ARG_PARAM3, maddress);
        args.putString(ARG_PARAM4, String.valueOf(mphone));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mfullName = getArguments().getString(ARG_PARAM1);
            mage = getArguments().getString(ARG_PARAM2);
            maddress = getArguments().getString(ARG_PARAM3);
            mphone = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}