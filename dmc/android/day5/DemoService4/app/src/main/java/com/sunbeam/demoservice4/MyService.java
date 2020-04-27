package com.sunbeam.demoservice4;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private int mCounter;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startCounter(0);
        return super.onStartCommand(intent, flags, startId);
    }

    private void startCounter(final int iteration) {
        mCounter = iteration;

        if(mCounter % 10 == 0) {
            Intent intent = new Intent("COUNTER_UPDATE");

            intent.putExtra("counter", mCounter);

            sendBroadcast(intent);


        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(getClass().getName(), "++++ Service iteration " + iteration);
                startCounter(iteration + 1);
            }

        }, 200);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
