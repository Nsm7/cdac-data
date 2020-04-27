package com.sunbeaminfo.application5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> movies = new ArrayList<>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        movies.add("Toy Story 1");
        movies.add("Cars 1");
        movies.add("Cars 2");
        movies.add("Cars 3");
        movies.add("Moana");

        adapter = new ArrayAdapter<String>(this, R.layout.movie_item, movies);
        listView.setAdapter(adapter);
    }
}
