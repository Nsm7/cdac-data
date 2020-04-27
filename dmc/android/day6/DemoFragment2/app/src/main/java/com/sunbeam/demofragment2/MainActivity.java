package com.sunbeam.demofragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnFrag1 = findViewById(R.id.btn_frag1);
        Button btnFrag2 = findViewById(R.id.btn_frag2);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btn_frag1) {

                    FragmentDemo1 frag = FragmentDemo1.newInstance();

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fl_container, frag);
                    transaction.commit();

                } else {
                    FragmentDemo2 frag = FragmentDemo2.newInstance();

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fl_container, frag);
                    transaction.commit();
                }
            }
        };

        btnFrag1.setOnClickListener(listener);
        btnFrag2.setOnClickListener(listener);
    }
}
