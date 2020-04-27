package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.adapter.ContactAdapter;
import com.sunbeaminfo.application4.db.DBHelper;
import com.sunbeaminfo.application4.model.Contact;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity implements ContactAdapter.ContactAdapterActionListener {

    EditText editSearch;

    RecyclerView recyclerView;
    ContactAdapter adapter;
    ArrayList<Contact> contacts = new ArrayList<>();

    RadioButton radioName, radioCity, radioAddress, radioEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = findViewById(R.id.editSearch);

        radioName = findViewById(R.id.radioName);
        radioCity = findViewById(R.id.radioCity);
        radioAddress = findViewById(R.id.radioAddress);
        radioEmail= findViewById(R.id.radioEmail);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ContactAdapter(this, contacts, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    public void onSearch(View v) {
        String text = editSearch.getText().toString();
        if (text.length() == 0) {
            Toast.makeText(this, "Enter search criteria", Toast.LENGTH_SHORT).show();
        } else {
            contacts.clear();

            String searchField = "";
            if (radioName.isChecked()) {
                searchField = "name";
            } else if (radioAddress.isChecked()) {
                searchField = "address";
            } else if (radioCity.isChecked()) {
                searchField = "city";
            } else if (radioEmail.isChecked()) {
                searchField = "email";
            }

            String query = "select * from contact where " + searchField + " like '%" + text + "%'";
            Log.e("SearchActivity", "Query: " + query);

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            if (!cursor.isAfterLast()) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setAddress(cursor.getString(2));
                    contact.setEmail(cursor.getString(3));
                    contact.setPhone(cursor.getString(4));
                    contact.setCity(cursor.getString(5));

                    contacts.add(contact);

                    cursor.moveToNext();
                }
            }

            cursor.close();
            db.close();

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onEdit(int position) {

    }

    @Override
    public void onDelete(int position) {

    }
}
