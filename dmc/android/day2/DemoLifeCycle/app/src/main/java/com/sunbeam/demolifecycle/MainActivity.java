package com.sunbeam.demolifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("++++MainActivity", "in OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("++++MainActivity", "in OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("++++MainActivity", "in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("++++MainActivity", "in OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("++++MainActivity", "in OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("++++MainActivity", "in OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("++++MainActivity", "in OnDestroy");
    }
}
