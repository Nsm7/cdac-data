package com.sunbeaminfo.application4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    EditText editFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editFirstName = findViewById(R.id.editFirstName);
    }

    public void onSave(View v) {
        String firstName = editFirstName.getText().toString();

        // create an intent which will carry the required data
        Intent intent = new Intent();

        // add the key-value pair
        intent.putExtra("firstName", firstName);

        // send the intent to the previous activity
        setResult(0, intent);

        finish();
    }

    public void onCancel(View v) {
        finish();
    }
}
