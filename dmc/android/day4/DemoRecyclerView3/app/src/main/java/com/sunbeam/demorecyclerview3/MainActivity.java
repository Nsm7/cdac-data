package com.sunbeam.demorecyclerview3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvList = findViewById(R.id.rv_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);

        ArrayList<User> data = new ArrayList<>();
        for(int index = 0; index < 50; ++index) {
            User newUser = new User("User " + index, "Address Pune 4110" + index);
            data.add(newUser);
        }

        ListAdapter adapter = new ListAdapter(data);
        rvList.setAdapter(adapter);
    }
}
