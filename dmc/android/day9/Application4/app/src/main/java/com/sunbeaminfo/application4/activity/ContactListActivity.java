package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.adapter.ContactAdapter;
import com.sunbeaminfo.application4.db.DBHelper;
import com.sunbeaminfo.application4.model.Contact;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity implements ContactAdapter.ContactAdapterActionListener {

    RecyclerView recyclerView;

    ContactAdapter adapter;

    ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ContactAdapter(this, contacts, this);
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

        String columns[] = new String[] {"id", "name", "address", "email", "phone", "city"};

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
                contact.setCity(cursor.getString(5));

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAdd) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        } else if (id == R.id.menuClose) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEdit(int position) {
        Contact contact = contacts.get(position);
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    @Override
    public void onDelete(final int position) {

        // dialog: show()
        // activity: startActivity..

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("confirmation");
        builder.setMessage("Are you sure you want to delete this contact?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Contact contact = contacts.get(position);

                DBHelper helper = new DBHelper(ContactListActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                db.delete("contact", "id=?", new String[] {"" + contact.getId()});
                db.close();

                // load the fresh data
                loadContacts();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
