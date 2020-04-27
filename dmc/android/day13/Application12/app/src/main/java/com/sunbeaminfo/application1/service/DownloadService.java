package com.sunbeaminfo.application1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class DownloadService extends Service {

    public void performJob() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("DownloadService", "going to sleep :)");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("DownloadService", "coming out of sleep :(");

            }
        }).start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("DownloadService", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("DownloadService", "onBind");

        performJob();

        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("DownloadService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("DownloadService", "onDestroy");
    }
}
