package com.sunbeam.demoservice3;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public static int sCounter = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startCounter(0);
        return super.onStartCommand(intent, flags, startId);
    }

    private void startCounter(final int iteration) {
        sCounter = iteration;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(getClass().getName(), "++++ Service iteration " + iteration);
                startCounter(iteration + 1);
            }

        }, 1000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
