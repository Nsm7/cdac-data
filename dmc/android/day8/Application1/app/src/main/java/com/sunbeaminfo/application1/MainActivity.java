package com.sunbeaminfo.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> movies = new ArrayList<>();

    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies.add("Cars");
        movies.add("Moana");
        movies.add("Kung Fu Panda");
        movies.add("Toy Story");
        movies.add("Finding Dory");
        movies.add("Finding Nemo");
        movies.add("Frozen");

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movie = movies.get(position);
                Toast.makeText(MainActivity.this, "Selected Movie: " + movie, Toast.LENGTH_SHORT).show();
            }
        });


        // register for context menu
        registerForContextMenu(listView);
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
        if (item.getTitle().equals("add")) {

        } else if (item.getTitle().equals("close")) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // context menu


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Options");

        menu.add("update");
        menu.add("delete");
        menu.add("cancel");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // get the clicked item position
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position = info.position;


        if (item.getTitle().equals("update")) {

        } else if (item.getTitle().equals("delete")) {

            movies.remove(position);
            adapter.notifyDataSetChanged();

        } else if (item.getTitle().equals("cancel")) {

        }
        return super.onContextItemSelected(item);
    }
}
