package com.sunbeam.demoservice4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvCounter;
    private Button mBtnService;
    private Button mBtnStopService;
    private CounterReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvCounter = findViewById(R.id.tv_counter);
        mBtnService = findViewById(R.id.btn_service);
        mBtnStopService = findViewById(R.id.btn_stop_service);

        mBtnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        mBtnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopServiceIntent = new Intent(MainActivity.this, MyService.class);
                stopService(stopServiceIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mReceiver = new CounterReceiver();

        IntentFilter filter = new IntentFilter("COUNTER_UPDATE");

        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        if(null != mReceiver) {
            unregisterReceiver(mReceiver);
        }
        super.onPause();

    }

    class CounterReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getIntExtra("counter", 0);

            mTvCounter.setText(counter + "");
        }
    }
}
