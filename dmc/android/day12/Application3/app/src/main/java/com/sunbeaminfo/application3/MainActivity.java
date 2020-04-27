package com.sunbeaminfo.application3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editName) EditText editName;
    @BindView(R.id.editAddress) EditText editAddress;
    @BindView(R.id.editPhone) EditText editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onInsert(View v) {
        ContentValues values = new ContentValues();
        values.put("name", editName.getText().toString());
        values.put("address", editAddress.getText().toString());
        values.put("phone", editPhone.getText().toString());

        Uri uri = Uri.parse("content://com.sunbeaminfo.application2");

        ContentResolver resolver = getContentResolver();
        resolver.insert(uri, values);
    }

    public void onQuery(View v) {
        Uri uri = Uri.parse("content://com.sunbeaminfo.application2");

        String columns[] = {"id", "name", "address", "phone"};
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, columns, null, null, null);
        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String phone = cursor.getString(3);

                Log.e("MainActivity", "id: " + id);
                Log.e("MainActivity", "name: " + name);
                Log.e("MainActivity", "address: " + address);
                Log.e("MainActivity", "phone: " + phone);

                cursor.moveToNext();
            }
        }

        cursor.close();
    }


}
