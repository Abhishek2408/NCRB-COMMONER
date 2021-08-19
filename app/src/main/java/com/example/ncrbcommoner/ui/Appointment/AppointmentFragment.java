package com.example.ncrbcommoner.ui.Appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ncrbcommoner.Appoint_data;
import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.databinding.FragmentAppointmentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AppointmentFragment extends Fragment {

   private FragmentAppointmentBinding binding;
   EditText commonername, commonernumber, reason;
   Button ask;
   Spinner select_person;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentAppointmentBinding.inflate(getLayoutInflater(), container, false);
        View root = binding.getRoot();


        select_person = binding.personSpinner;
        commonername = binding.editName;
        commonernumber = binding.editNumber;
        reason = binding.reasonEdit;

        ask = binding.btnAsk;



        List<String> police_users = new ArrayList<String>();
        //Setting list to spinner

        ArrayAdapter ad = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, police_users);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_person.setAdapter(ad);

        //fetching user name in spinner

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot usersnap : snapshot.getChildren()){
                    String usersname = usersnap.child("f_name").getValue(String.class);
                    String userlname = usersnap.child("l_name").getValue(String.class);
                    String user = usersname+" "+userlname;
                    ad.add(user);
                }
                ad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = select_person.getSelectedItem().toString();
                String comm_name = commonername.getText().toString();
                String comm_no = commonernumber.getText().toString();
                String rea_son = reason.getText().toString();
                String status = "Reject";

                if(comm_name.isEmpty()) {
                    commonername.setError("Full name is required!");
                    commonername.requestFocus();
                    return;
                }
                if(username.isEmpty()) {
                    commonername.setError("Full name is required!");
                    commonername.requestFocus();
                    return;
                }
                if(comm_no.isEmpty()) {
                    commonernumber.setError("Phone number is required!");
                    commonernumber.requestFocus();
                    return;
                }

                //Inserting data into Commoner Appointment Records

                Appoint_data appoint_data = new Appoint_data(username,comm_name,comm_no,rea_son,status);
                FirebaseDatabase.getInstance().getReference().child("Commoner Appointment Records").push().setValue(appoint_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),"Appointment Placed",Toast.LENGTH_SHORT).show();
                            commonername.setText("");
                            commonernumber.setText("");
                        }else {
                            Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}