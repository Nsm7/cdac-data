package com.sunbeam.demointentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        TextView tvMessage = findViewById(R.id.tv_email);
        tvMessage.setText(email);
    }
}
