package com.sunbeaminfo.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner, spinnerCodes;

    ArrayAdapter<String> adapter, adapterCodes;

    String cities[] = {"Pune", "Mumbai", "Karad", "Satara", "Sangli"};

    String codes[] = {"+91", "+93", "+1", "+5", "+10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        adapterCodes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, codes);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinnerCodes = findViewById(R.id.spinnerCodes);
        spinnerCodes.setAdapter(adapterCodes);
    }
}
