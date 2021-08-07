package com.example.ncrbcommoner.ui.Appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.databinding.FragmentAppointmentBinding;

public class AppointmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {

   private FragmentAppointmentBinding binding;
   Spinner select_person;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentAppointmentBinding.inflate(getLayoutInflater(), container, false);
        View root = binding.getRoot();

        String[] doctype = { "Police Constable","Police Naik", "Head Constable","Assistant Police Sub-Inspector","Police Sub-Inspector" };
        select_person = binding.personSpinner;
        select_person.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, doctype);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        select_person.setAdapter(ad);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}