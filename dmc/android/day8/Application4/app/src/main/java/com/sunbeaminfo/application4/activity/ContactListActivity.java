package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.adapter.ContactAdapter;
import com.sunbeaminfo.application4.db.DBHelper;
import com.sunbeaminfo.application4.model.Contact;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ContactAdapter adapter;

    ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ContactAdapter(this, contacts);
        recyclerView.setAdapter(adapter);

        // layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the contacts
        loadContacts();
    }

    // database

    private void loadContacts() {
        // clear the existing contents
        contacts.clear();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = new String[] {"id", "name", "address", "email", "phone"};

        Cursor cursor = db.query("contact", columns, null, null, null, null, null);
        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setAddress(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setPhone(cursor.getString(4));

                contacts.add(contact);

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        // refresh the recycler view
        adapter.notifyDataSetChanged();
    }

    // options menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("add");
        menu.add("close");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = (String) item.getTitle();
        if (title.equals("add")) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        } else if (title.equals("close")) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
