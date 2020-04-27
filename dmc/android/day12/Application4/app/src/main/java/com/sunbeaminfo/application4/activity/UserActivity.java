package com.sunbeaminfo.application4.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.constants.Constants;
import com.sunbeaminfo.application4.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<>();
    ArrayAdapter<User> adapter;

    @BindView(R.id.editName) EditText editName;
    @BindView(R.id.editAddress) EditText editAddress;
    @BindView(R.id.listView) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);
    }

    public void onInsert(View v) {
        String name = editName.getText().toString();
        String address = editAddress.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        } else if (address.length() == 0) {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("address", address);

            ContentResolver resolver = getContentResolver();
            resolver.insert(Constants.CONTENT_URI_USER, values);
        }

    }

    public void onQuery(View v) {
        users.clear();

        String columns[] = {"id", "name", "address"};
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Constants.CONTENT_URI_USER, columns, null, null, null);

        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setAddress(cursor.getString(cursor.getColumnIndex("address")));

                users.add(user);

                cursor.moveToNext();
            }
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
