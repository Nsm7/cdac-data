package com.sunbeaminfo.application3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sunbeaminfo.application3.adapter.CountryAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    CountryAdapter adapter;

    ArrayList<String> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countries.add("India");
        countries.add("USA");
        countries.add("UK");
        countries.add("Japan");


        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CountryAdapter(this, countries);
        recyclerView.setAdapter(adapter);

        // layout manager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }
}
