package com.sunbeaminfo.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sunbeaminfo.application2.db.DBHelper;
import com.sunbeaminfo.application2.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editName;

    ListView listView;
    ArrayList<Country> countries = new ArrayList<>();
    ArrayAdapter<Country> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Country>(this, android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country = countries.get(position);
                Toast.makeText(MainActivity.this, "country: " + country.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // register for context menu
        registerForContextMenu(listView);
    }

    public void onInsert(View v) {
        // insert into country (name) values ('india');

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", editName.getText().toString());
        db.insert("country", null, values);

        db.close();

        // get the latest items
        onQuery(null);
    }

    public void onQuery(View v) {
        // select id, name from country;

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = new String[] {"id", "name"};
        Cursor cursor = db.query("country", columns, null, null, null, null, null);

        // clear all the existing items
        countries.clear();

        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);

//                Log.e("MainActivity", "Id: " + id);
//                Log.e("MainActivity", "Name: " + name);

                countries.add(new Country(id, name));
                cursor.moveToNext();

            }
        }

        cursor.close();

        db.close();

        // refresh the listview
        adapter.notifyDataSetChanged();
    }

    // for context menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("update");
        menu.add("delete");
        menu.add("cancel");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getTitle().equals("update")) {
            onUpdate(position);
        } else if (item.getTitle().equals("delete")) {
            onDelete(position);
        } else if (item.getTitle().equals("cancel")) {

        }
        return super.onContextItemSelected(item);
    }

    private void onUpdate(int position) {
        Country country = countries.get(position);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // update country set name = '' where id = <>

        ContentValues values = new ContentValues();
        values.put("name", "sri lanka");

        db.update("country", values, "id=?", new String[] {"" + country.getId()});

        db.close();

        // get the latest items
        onQuery(null);
    }

    private void onDelete(int position) {
        Country country = countries.get(position);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // delete from country where id = <>

        db.delete("country", "id=? or name=?", new String[]{ "" + country.getId(), country.getName() });

        db.close();

        // get the latest items
        onQuery(null);
    }
}
