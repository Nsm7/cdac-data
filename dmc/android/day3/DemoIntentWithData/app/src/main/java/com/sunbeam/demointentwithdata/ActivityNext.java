package com.sunbeam.demointentwithdata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityNext extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        String dataFromIntent = intent.getStringExtra("data");

        TextView tvDataFromIntent = findViewById(R.id.tv_intent_data);
        tvDataFromIntent.setText(dataFromIntent);
    }
}
