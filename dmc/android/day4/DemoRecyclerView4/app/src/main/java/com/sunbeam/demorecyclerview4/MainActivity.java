package com.sunbeam.demorecyclerview4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
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

        ArrayList<User> userList = new ArrayList<>();
        for(int index = 0; index < 50; ++index) {
            User user = new User("name " + index, "handle " + index );
            userList.add(user);
        }

        UserAdapter adapter = new UserAdapter(userList);
        rvList.setAdapter(adapter);
    }
}
