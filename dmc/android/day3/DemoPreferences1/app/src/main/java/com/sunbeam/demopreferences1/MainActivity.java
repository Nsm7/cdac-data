package com.sunbeam.demopreferences1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create object of Shared preference
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        counter = prefs.getInt("counter", 0);
//
        final TextView tvCounter = findViewById(R.id.tv_counter);
        Button btnCounter = findViewById(R.id.btn_counter);
//
        tvCounter.setText(counter + "");

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("counter", counter);
                editor.commit();

                tvCounter.setText(counter + "");
            }
        });
    }
}
