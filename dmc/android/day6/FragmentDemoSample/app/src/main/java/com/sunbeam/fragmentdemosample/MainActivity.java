package com.sunbeam.fragmentdemosample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentDemo fragment = FragmentDemo.newInstance();

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fl_container, fragment);

        transaction.commit();


//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_container, fragment)
//                .commit();
    }
}
