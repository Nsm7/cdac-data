package com.sunbeam.demobroadcast5;

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

        TextView tvBroadcast = findViewById(R.id.tv_broadcast);

        tvBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("send_data");

                intent.putExtra("data", "My test data");

                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mReceiver = new MyReceiver();

        IntentFilter filter = new IntentFilter("send_data");

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
            String dataInBroadcast = intent.getStringExtra("data");

            String data = "Data in brodacast : " + dataInBroadcast;
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
        }
    }
}
