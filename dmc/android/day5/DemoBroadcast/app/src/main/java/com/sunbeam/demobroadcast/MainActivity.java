package com.sunbeam.demobroadcast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMessage = findViewById(R.id.tv_message);
        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create broadcast
                Intent broadcastIntent = new Intent("test_channel");

                // Send boradcast
                sendBroadcast(broadcastIntent);
            }
        });

        // Create reciever
        MyReceiver receiver = new MyReceiver();

        // Create filter to tune into a specific channel
        IntentFilter filter = new IntentFilter("test_channel");

        // Register the receiver with current activity to execute onReceive
        // when a boradcast is received
        registerReceiver(receiver, filter);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "Boradcast received", Toast.LENGTH_SHORT).show();
        }
    }
}
