package com.sunbeam.fragmentdemosample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDemo extends Fragment {

    public static FragmentDemo newInstance() {

        FragmentDemo fragment = new FragmentDemo();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);

        // Create references to child view to manipulate or handle user interaction
        final TextView tvCounter = view.findViewById(R.id.tv_counter);
        Button btnAdd = view.findViewById(R.id.btn_counter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tvCount = tvCounter.getText().toString();
                int count = Integer.parseInt(tvCount);
                count++;
                tvCounter.setText(count + "");
            }
        });

        return view;
    }
}
