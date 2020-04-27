package com.sunbeaminfo.application1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // customise the action bar


        // get the current action bar
        ActionBar actionBar = getSupportActionBar();

        // change the title
        actionBar.setTitle("  Welcome");

        // change the icon
        actionBar.setIcon(R.drawable.ic_logo);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    public void onLaunch(View v) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }


    // options menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        // inflate menu xml
        inflater.inflate(R.menu.menu_main_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAdd) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        } else if (id == R.id.menuClose) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
