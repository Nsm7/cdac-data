package com.sunbeaminfo.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editData = findViewById(R.id.editData);
    }

    public void onSend(View v) {
        String data = editData.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("data", data);

        setResult(0, intent);
        finish();
    }
}
