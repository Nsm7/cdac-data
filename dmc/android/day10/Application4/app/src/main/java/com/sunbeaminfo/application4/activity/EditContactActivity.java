package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.db.DBHelper;
import com.sunbeaminfo.application4.model.Contact;

public class EditContactActivity extends BaseActivity {

    EditText editName, editAddress, editPhone, editEmail;

    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);

        // get the contact needs to be editted
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");

        // show existing details
        editName.setText(contact.getName());
        editAddress.setText(contact.getAddress());
        editEmail.setText(contact.getEmail());
        editPhone.setText(contact.getPhone());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_contact_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {

            // update

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", editName.getText().toString());
            values.put("address", editAddress.getText().toString());
            values.put("email", editEmail.getText().toString());
            values.put("phone", editPhone.getText().toString());

            db.update("contact", values, "id=?", new String[] { "" + contact.getId() });

            db.close();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
