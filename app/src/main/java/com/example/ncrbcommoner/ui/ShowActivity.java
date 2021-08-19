package com.example.ncrbcommoner.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ncrbcommoner.R;
import com.example.ncrbcommoner.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class ShowActivity extends AppCompatActivity {

    TextView tv_name, tv_user, tv_reason, tv_date, tv_time;
    DatabaseReference databaseReference;
    String showname;
    Button okbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        String resComm;
        Bundle extras = getIntent().getExtras();
        resComm = extras.getString("result");

        okbtn = findViewById(R.id.btn_ask);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Approved Appointment");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataname: snapshot.getChildren()){
                    Notify_appointdata u1 = dataname.getValue(Notify_appointdata.class);

                    if (u1.getCommoner_name().equals(resComm)) {

                        //name
                        showname = u1.getCommoner_name();
                        tv_name = findViewById(R.id.name_edit);
                        tv_name.setText(showname);

                        //user
                        tv_user = findViewById(R.id.user_edit);
                        tv_user.setText(u1.getUser());

                        //reason
                        tv_reason = findViewById(R.id.reason_edit);
                        tv_reason.setText(u1.getReason());

                        //date
                        tv_date = findViewById(R.id.date_ed);
                        String d = u1.getDate();
                        tv_date.setText(d);

                        //time
                        tv_time = findViewById((R.id.time_ed));
                        String t = u1.getTime();
                        tv_time.setText(t);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}