package com.example.ncrbcommoner.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    TextView tv;
    DatabaseReference databaseReference;
    String showname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        String res;
        Bundle extras = getIntent().getExtras();
        res = extras.getString("result");


        databaseReference = FirebaseDatabase.getInstance().getReference("Users Commoner");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataname: snapshot.getChildren()){
                    User u1 = dataname.getValue(User.class);
                    showname = u1.getUsername();
                    tv = findViewById(R.id.showtext);
                    tv.setText(showname);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}