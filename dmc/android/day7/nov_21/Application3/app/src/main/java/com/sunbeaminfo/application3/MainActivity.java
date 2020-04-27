package com.sunbeaminfo.application3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFirstName = findViewById(R.id.editFirstName);
    }

    public void onCancel(View v) {
        finish();
    }

    public void onShow(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("firstName", editFirstName.getText().toString());
        startActivity(intent);
    }
}
