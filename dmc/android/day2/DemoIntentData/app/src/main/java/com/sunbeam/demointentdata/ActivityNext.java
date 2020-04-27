package com.sunbeam.demointentdata;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityNext extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next);

        TextView tvData = findViewById(R.id.tv_data);

        String data = getIntent().getStringExtra("data");

        tvData.setText(data);
    }
}
