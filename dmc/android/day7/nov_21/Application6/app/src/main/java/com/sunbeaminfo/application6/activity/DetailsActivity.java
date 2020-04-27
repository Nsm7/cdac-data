package com.sunbeaminfo.application6.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sunbeaminfo.application6.R;

public class DetailsActivity extends AppCompatActivity {


    TextView textName, textAddress, textEmail, textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textEmail = findViewById(R.id.textEmail);
        textPhone = findViewById(R.id.textPhone);

        Intent intent = getIntent();
        textName.setText("Name: " + intent.getStringExtra("name"));
        textAddress.setText("Address: " + intent.getStringExtra("address"));
        textEmail.setText("Email: " + intent.getStringExtra("email"));
        textPhone.setText("Phone: " + intent.getStringExtra("phone"));
    }

    public void onBack(View view) {
        finish();
    }
}
