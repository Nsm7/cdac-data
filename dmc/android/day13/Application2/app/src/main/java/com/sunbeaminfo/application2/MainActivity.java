package com.sunbeaminfo.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("MainActivity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("MainActivity", "onServiceDisconnected");
        }
    };

    public void onStart(View v) {
        Intent intent = new Intent();
        intent.setClassName("com.sunbeaminfo.application1", "com.sunbeaminfo.application1.service.DownloadService");
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void onStop(View v) {
        unbindService(connection);
    }
}
