package com.sunbeam.demobroadcast4;

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

    private Myreceiver1 mReceiver1;
    private Myreceiver2 mReceiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvBr1 = findViewById(R.id.tv_br1);
        TextView tvBr2 = findViewById(R.id.tv_br2);

        tvBr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("br1");

                sendBroadcast(intent);
            }
        });

        tvBr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("br2");

                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mReceiver1 = new Myreceiver1();
        mReceiver2 = new Myreceiver2();

        IntentFilter filter1 = new IntentFilter("br1");
        IntentFilter filter2 = new IntentFilter("br2");

        registerReceiver(mReceiver1, filter1);
        registerReceiver(mReceiver2, filter2);

    }

    @Override
    protected void onPause() {
        if (null != mReceiver1) {
            unregisterReceiver(mReceiver1);
        }

        if (null != mReceiver2) {
            unregisterReceiver(mReceiver2);
        }

        super.onPause();
    }

    class Myreceiver1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Br1", Toast.LENGTH_SHORT).show();
        }
    }

    class Myreceiver2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Br2", Toast.LENGTH_SHORT).show();
        }
    }
}
