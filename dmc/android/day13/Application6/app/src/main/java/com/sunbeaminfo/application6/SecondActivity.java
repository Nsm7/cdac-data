package com.sunbeaminfo.application6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.sunbeaminfo.application6.fragment.CarListFragment;
import com.sunbeaminfo.application6.fragment.MobileListFragment;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onMobileFragment(View v) {
        MobileListFragment fragment = new MobileListFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layoutContent, fragment);
        transaction.commit();
    }

    public void onCarFragment(View v) {
        CarListFragment fragment = new CarListFragment();

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.layoutContent, fragment)
            .commit();

    }
}
