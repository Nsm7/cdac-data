package com.sunbeaminfo.application4.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.db.DBHelper;

public class AddContactActivity extends BaseActivity {

    EditText editName, editAddress, editPhone, editEmail;

    String cities[] = {"Pune", "Mumbai", "Satara"};
    ArrayAdapter<String> adapterCities;
    Spinner spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);

        spinnerCity = findViewById(R.id.spinnerCity);
        adapterCities = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        spinnerCity.setAdapter(adapterCities);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_contact_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSave) {
            onSave();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSave() {
        if (editName.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        } else if (editAddress.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
        } else {

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            int position = spinnerCity.getSelectedItemPosition();

            ContentValues values = new ContentValues();
            values.put("name", editName.getText().toString());
            values.put("address", editAddress.getText().toString());
            values.put("email", editEmail.getText().toString());
            values.put("phone", editPhone.getText().toString());
            values.put("city", cities[position]);

            db.insert("contact", null, values);

            db.close();

            Toast.makeText(this, "Added new contact successfully", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}
