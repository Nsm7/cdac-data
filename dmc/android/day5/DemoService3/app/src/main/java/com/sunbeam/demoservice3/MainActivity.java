package com.sunbeam.demoservice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvCounter = findViewById(R.id.tv_counter);
        Button btnStartService = findViewById(R.id.btn_service);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);

                startService(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mTvCounter.setText(MyService.sCounter + "");
    }
}
