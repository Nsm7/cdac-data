package com.sunbeam.demorecyclerviewintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityUser extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String data = intent.getStringExtra("user");

        TextView tvName = findViewById(R.id.tv_name);

        tvName.setText(data);
    }
}
