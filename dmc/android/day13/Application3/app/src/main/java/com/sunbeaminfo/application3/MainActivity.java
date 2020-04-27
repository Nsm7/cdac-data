package com.sunbeaminfo.application3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.sunbeaminfo.application3.service.MyService;

public class MainActivity extends AppCompatActivity {
    MyService myService;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myService = myBinder.getMyServiceInstance();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
        }
    };

    public void onPerformJob(View v) {
        myService.downloadFile();
    }

    public void onStart(View v) {
        bindService(new Intent(this, MyService.class), connection, BIND_AUTO_CREATE);
    }

    public void onStop(View v) {
        unbindService(connection);
    }
}
