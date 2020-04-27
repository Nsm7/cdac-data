package com.sunbeaminfo.application4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView textFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textFirstName = findViewById(R.id.textFirstName);
    }

    public void onGet(View view) {
        Intent intent = new Intent(this, InputActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, 0);
    }

    public void onClose(View view) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String firstName = data.getStringExtra("firstName");
        textFirstName.setText("First Name: " + firstName);
    }
}
