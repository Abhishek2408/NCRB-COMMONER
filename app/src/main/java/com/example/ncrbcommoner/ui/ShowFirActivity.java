package com.example.ncrbcommoner.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ncrbcommoner.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class ShowFirActivity extends AppCompatActivity {

    TextView tv_name, tv_email, tv_no, tv_suspect, tv_statement, tv_area, tv_date, tv_time, tv_evidence;
    Button okbtn;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fir);

        String resName;
        Bundle extras = getIntent().getExtras();
        resName = extras.getString("result");

        okbtn = findViewById(R.id.submitBtn);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("Approved Cases");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot datashot: snapshot.getChildren()){
                    Fir_getNotify firdata = datashot.getValue(Fir_getNotify.class);

                    if(resName.equals(firdata.getName())){
                        //setting values to textview
                        tv_name = findViewById(R.id.name_edit);
                        tv_no = findViewById(R.id.no_edit);
                        tv_email = findViewById(R.id.email_edit);
                        tv_suspect = findViewById(R.id.suspect_edit);
                        tv_statement = findViewById(R.id.statement_edit);
                        tv_area = findViewById(R.id.area_edit);
                        tv_date = findViewById(R.id.date_edit);
                        tv_time = findViewById(R.id.time_edit);
                        tv_evidence = findViewById(R.id.evidence_edit);

                        tv_name.setText(firdata.getName());
                        tv_email.setText(firdata.getEmail());
                        tv_no.setText(firdata.getPhone());
                        tv_suspect.setText(firdata.getSuspect());
                        tv_statement.setText(firdata.getStatement());
                        tv_area.setText(firdata.getArea());
                        tv_date.setText(firdata.getDate());
                        tv_time.setText(firdata.getTime());

                        if(firdata.getEvid().equals("")){
                            tv_evidence.setText("No");
                        }
                        else{
                            tv_evidence.setText(firdata.getEvid());
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}