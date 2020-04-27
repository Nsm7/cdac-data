package com.sunbeam.demorecyclerviewintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        ArrayList<String> data = new ArrayList<>();
        for(int index = 0; index < 50; ++index) {
            data.add("User " + index);
        }

        UserAdapter adapter = new UserAdapter(this, data);
        rvList.setAdapter(adapter);
    }
}
