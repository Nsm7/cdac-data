package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.db.DBHelper;

public class AddContactActivity extends AppCompatActivity {

    EditText editName, editAddress, editPhone, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
    }

    public void onSave(View view) {
        if (editName.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        } else if (editAddress.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
        } else {

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", editName.getText().toString());
            values.put("address", editAddress.getText().toString());
            values.put("email", editEmail.getText().toString());
            values.put("phone", editPhone.getText().toString());

            db.insert("contact", null, values);

            db.close();

            Toast.makeText(this, "Added new contact successfully", Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    public void onCancel(View view) {
        finish();
    }
}
