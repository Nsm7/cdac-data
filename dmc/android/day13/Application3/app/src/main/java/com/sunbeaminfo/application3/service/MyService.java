package com.sunbeaminfo.application3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public class MyBinder extends Binder {

        public MyService getMyServiceInstance() {
            return MyService.this;
        }
    }

    public void downloadFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "download file started");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "download file finished");
            }
        }).start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind()");

        // if onBind returns null, nothing happes
        // - onServiceConnected from MainActivity never gets called

        // if onBind returns not-null
        // - onServiceConnected from MainActivity gets called immediately

        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }
}
