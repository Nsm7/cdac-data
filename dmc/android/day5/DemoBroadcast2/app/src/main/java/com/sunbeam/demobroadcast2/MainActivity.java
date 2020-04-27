package com.sunbeam.demobroadcast2;

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

        TextView tvMessage = findViewById(R.id.tv_message);

        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent broadcast = new Intent("my_channel");

                sendBroadcast(broadcast);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Create receiver
        mReceiver = new MyReceiver();

        IntentFilter filter = new IntentFilter("my_channel");

        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {

        // unregister the receiver before onPause
        unregisterReceiver(mReceiver);

        super.onPause();
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Received broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
