package com.sunbeaminfo.application3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textFirstName = findViewById(R.id.textFirstName);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");

        textFirstName.setText("First Name: " + firstName);
    }

    public void onBack(View view) {
        finish();
    }
}
