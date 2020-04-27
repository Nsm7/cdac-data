package com.sunbeam.demolifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter;

    private TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCounter = findViewById(R.id.btn_counter);
        tvCounter = findViewById(R.id.tv_counter);

        counter = 0;

        tvCounter.setText(counter + "");

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                tvCounter.setText(counter + "");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        counter = 0;

        tvCounter.setText(counter + "");
    }
}
