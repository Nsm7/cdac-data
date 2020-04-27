package com.sunbeaminfo.application6.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sunbeaminfo.application6.R;

public class InputActivity extends AppCompatActivity {

    EditText editName, editAddress, editPhone, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
    }

    public void onSave(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", editName.getText().toString());
        intent.putExtra("address", editAddress.getText().toString());
        intent.putExtra("email", editEmail.getText().toString());
        intent.putExtra("phone", editPhone.getText().toString());
        setResult(0, intent);

        finish();
    }

    public void onCancel(View v) {
        finish();
    }
}
