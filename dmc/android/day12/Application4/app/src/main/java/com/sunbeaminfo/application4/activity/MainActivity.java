package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sunbeaminfo.application4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSelectCar(View v) {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    }

    public void onSelectMobile(View v) {
        Intent intent = new Intent(this, MobileActivity.class);
        startActivity(intent);
    }

    public void onSelectUser(View v) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
