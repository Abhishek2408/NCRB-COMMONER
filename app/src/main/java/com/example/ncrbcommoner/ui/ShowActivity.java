package com.example.ncrbcommoner.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ncrbcommoner.R;


public class ShowActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        String res;
        Bundle extras = getIntent().getExtras();
        res = extras.getString("result");
        tv = findViewById(R.id.showtext);
        tv.setText(res);
    }
}