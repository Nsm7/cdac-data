package com.sunbeaminfo.application1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textData;
    CheckBox checkboxVisible;
    View viewBox;
    Switch switchVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = findViewById(R.id.textData);
        viewBox = findViewById(R.id.viewBox);
        checkboxVisible = findViewById(R.id.checkboxVisible);
        checkboxVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkboxVisible.isChecked()) {
                    viewBox.setVisibility(View.VISIBLE);
                } else {
                    viewBox.setVisibility(View.GONE);
                }
            }
        });

        switchVisible = findViewById(R.id.switchVisible);
        switchVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchVisible.isChecked()) {
                    viewBox.setVisibility(View.VISIBLE);
                } else {
                    viewBox.setVisibility(View.GONE);
                }
            }
        });
    }

    public void onGet(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String info = data.getStringExtra("data");
            textData.setText("Info: " + info);
        }
    }
}
