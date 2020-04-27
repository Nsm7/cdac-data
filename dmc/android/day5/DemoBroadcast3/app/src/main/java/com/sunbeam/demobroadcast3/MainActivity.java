package com.sunbeam.demobroadcast3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvBroadcast1 = findViewById(R.id.tv_broadcast1);
        TextView tvBroadcast2 = findViewById(R.id.tv_broadcast2);

        tvBroadcast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent broadcast = new Intent("broadcast1");
                sendBroadcast(broadcast);
            }
        });

        tvBroadcast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent broadcast = new Intent("broadcast2");
                sendBroadcast(broadcast);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mReceiver = new MyReceiver();

        IntentFilter filter = new IntentFilter("broadcast1");
        filter.addAction("broadcast2");
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        if(null != mReceiver) {
            unregisterReceiver(mReceiver);
        }
        super.onPause();
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context,
                    intent.getAction() + " Broadcast Received",
                    Toast.LENGTH_SHORT)
                .show();
        }
    }
}
