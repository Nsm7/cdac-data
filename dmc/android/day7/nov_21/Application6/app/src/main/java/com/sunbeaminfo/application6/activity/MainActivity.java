package com.sunbeaminfo.application6.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sunbeaminfo.application6.R;
import com.sunbeaminfo.application6.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts = new ArrayList<>();
    ArrayAdapter<Contact> adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Contact>(this, R.layout.item_contact, contacts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get the selected contact
                Contact contact = contacts.get(position);
                Toast.makeText(MainActivity.this, "Name: " + contact.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("name", contact.getName());
                intent.putExtra("address", contact.getAddress());
                intent.putExtra("email", contact.getEmail());
                intent.putExtra("phone", contact.getPhone());
                startActivity(intent);
            }
        });
    }

    // get the result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if adding contact is cancelled
        // dont do anything
        if (data == null) {
            return;
        }

        String name = data.getStringExtra("name");
        String address = data.getStringExtra("address");
        String phone = data.getStringExtra("phone");
        String email = data.getStringExtra("email");

        Contact contact = new Contact(name, address, email, phone);

        // refreshing the list view
        contacts.add(contact);
        adapter.notifyDataSetChanged();
    }


    // enable options menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add Contact");
        menu.add("Close");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Close")) {
            finish();
        } else if (item.getTitle().equals("Add Contact")) {
            Intent intent = new Intent(this, InputActivity.class);
            startActivityForResult(intent, 0);
        }
        return super.onOptionsItemSelected(item);
    }
}
