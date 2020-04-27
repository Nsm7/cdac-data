package com.sunbeam.demofragment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FragmentUserMessage.ActivityCallbacks {

    private TextView mTvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvMessage = findViewById(R.id.tv_message);

        FragmentUserMessage frag = FragmentUserMessage.newInstance();
        frag.setCallbackInterface(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_container, frag);
        transaction.commit();
    }


    @Override
    public void setMessage(String message) {
        mTvMessage.setText(message);
    }
}
