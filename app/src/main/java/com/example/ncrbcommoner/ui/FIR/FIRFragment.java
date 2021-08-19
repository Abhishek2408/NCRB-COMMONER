package com.example.ncrbcommoner.ui.FIR;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ncrbcommoner.Fir_submit;
import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.databinding.FragmentFirBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

// data to be uploaded = name,number,email,time,date,suspect_name,locality,statement,evidence=y/n, status

public class FIRFragment extends Fragment  implements View.OnClickListener{

    private FragmentFirBinding binding;
    private int mHour,mMinute, mYear, mMonth, mDay;
    String evidence="";
    EditText name, email , phno ,time , date , suspect_name, locality , statement;
    Button timebtn , datebtn, submit;
    Calendar c,c1;
    Switch ev;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_fir,container,false);


        name = root.findViewById(R.id.edit_name);
        email = root.findViewById(R.id.edit_email);
        phno = root.findViewById(R.id.edit_number);
        time = root.findViewById(R.id.time_edit);
        date = root.findViewById(R.id.date_edit);
        suspect_name = root.findViewById(R.id.edit_suspect);
        locality = root.findViewById(R.id.locality_edit);
        statement = root.findViewById(R.id.statement_edit);

        timebtn = root.findViewById(R.id.time_btn);
        datebtn = root.findViewById(R.id.date_btn);
        submit = root.findViewById(R.id.submit_firBtn);

        ev = root.findViewById(R.id.evidence_swap);

        timebtn.setOnClickListener(this);
        datebtn.setOnClickListener(this);
        submit.setOnClickListener(this);
        //switch button for evidence
        ev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    evidence = "Yes";
                }
                else{
                    evidence = "No";
                }
            }
        });

        return root;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_btn:
                c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                //Time Picker Launch
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int mins) {
                        time.setText(hour + ":" + mins);
                    }
                },mHour, mMinute, true);
                timePickerDialog.show();
                break;

            case R.id.date_btn:
                c1 = Calendar.getInstance();
                mYear = c1.get(Calendar.YEAR);
                mMonth = c1.get(Calendar.MONTH);
                mDay = c1.get(Calendar.DAY_OF_MONTH);

                //Date Picker Launch
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day+"-"+(month+1)+"-"+year);
                    }
                },mDay, mMonth, mYear);
                datePickerDialog.show();
                break;

            case  R.id.submit_firBtn:
                save_new_fir();
        }
    }
    private void save_new_fir() {
        String person_name = name.getText().toString().trim();
        String email_id = email.getText().toString().trim();
        String person_phno = phno.getText().toString().trim();
        String datef = date.getText().toString().trim();
        String timef = time.getText().toString().trim();
        String suspected_person = suspect_name.getText().toString().trim();
        String localityf = locality.getText().toString().trim();
        String fir_statement = statement.getText().toString().trim();
        String status = "Disapproved";

        //Validating Data
        if(person_name.isEmpty()) {
            name.setError("Full name is required!");
            name.requestFocus();
            return;
        }
        if(email_id.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(person_phno.isEmpty()) {
            phno.setError("Phone number is required!");
            phno.requestFocus();
            return;
        }
        if(datef.isEmpty()) {
            date.setError("Date is required!");
            date.requestFocus();
            return;
        }
        if(timef.isEmpty()) {
            time.setError("Time is required!");
            time.requestFocus();
            return;
        }
        if(suspected_person.isEmpty()) {
            suspect_name.setError("Suspect name is required!");
            suspect_name.requestFocus();
            return;
        }
        if(localityf.isEmpty()) {
            locality.setError("Locality is required!");
            locality.requestFocus();
            return;
        }
        if(fir_statement.isEmpty()) {
            statement.setError("Statement is required!");
            statement.requestFocus();
            return;
        }
       Fir_submit fir_s = new Fir_submit(person_name,email_id,person_phno,suspected_person,timef,datef,localityf,fir_statement,evidence,status);
        FirebaseDatabase.getInstance().getReference().child("Commoner Records").push().setValue(fir_s).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(),"FIR Registered",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    phno.setText("");
                    statement.setText("");
                }else {
                    Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}