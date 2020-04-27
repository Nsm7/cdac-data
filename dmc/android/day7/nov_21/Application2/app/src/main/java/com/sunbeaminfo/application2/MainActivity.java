package com.sunbeaminfo.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editFirstName, editLastName, editAge, editAddress, editEmail;
    Button buttonSave, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editAge = findViewById(R.id.editAge);
        editAddress = findViewById(R.id.editAddress);
        editEmail = findViewById(R.id.editEmail);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e(): error
                // Log.w(): warning
                // Log.d(): debug
                // Log.i(): information
                // Log.v(): verbose

                Log.d("MainActivity", "First Name: " + editFirstName.getText().toString());
                Log.d("MainActivity", "Last Name: " + editLastName.getText().toString());
                Log.d("MainActivity", "Email: " + editEmail.getText().toString());
                Log.d("MainActivity", "Address: " + editAddress.getText().toString());
                Log.d("MainActivity", "Age: " + editAge.getText().toString());
            }
        });

        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // closing the current activity
                finish();
            }
        });
    }
}
