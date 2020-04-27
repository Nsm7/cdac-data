package com.sunbeaminfo.application5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    ArrayList<Movie> movies = new ArrayList<>();

    ListView listView;
    ArrayAdapter<Movie> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movies.add(new Movie("Toy story 1", "Animation"));
        movies.add(new Movie("Cars 1", "Animation"));
        movies.add(new Movie("Bahubali", "Action"));

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<Movie>(this, R.layout.movie_item, movies);
        listView.setAdapter(adapter);
    }
}
