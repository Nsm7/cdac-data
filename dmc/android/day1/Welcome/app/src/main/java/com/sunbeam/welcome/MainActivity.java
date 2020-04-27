package com.sunbeam.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// AppCompatActivity defines this class as app screen
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This method declares which XML file to use to display UI
        setContentView(R.layout.activity_main);
    }
}
