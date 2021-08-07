package com.example.ncrbcommoner.ui.NOC;

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

import com.example.ncrbcommoner.databinding.FragmentFirBinding;
import com.example.ncrbcommoner.databinding.FragmentNocBinding;

public class NOCFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private FragmentNocBinding binding;
    Spinner selectfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNocBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String[] doctype = { "Bank Loan Noc","Body dispose noc", "land sailing Noc","Forest Noc","Fire Noc" };
        selectfile = binding.selectEdit;
        selectfile.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, doctype);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectfile.setAdapter(ad);


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