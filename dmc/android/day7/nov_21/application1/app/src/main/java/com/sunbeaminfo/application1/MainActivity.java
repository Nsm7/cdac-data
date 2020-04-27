package com.sunbeaminfo.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText("Hello From ActivityMain.java");
        layout.addView(textView);
//        setContentView(textView);

        TextView textView1 = new TextView(this);
        textView1.setText("Bye bye");
        layout.addView(textView1);
//        setContentView(textView1);

        setContentView(layout);

    }
}
