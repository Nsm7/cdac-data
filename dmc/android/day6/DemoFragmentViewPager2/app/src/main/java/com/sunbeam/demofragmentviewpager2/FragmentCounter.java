package com.sunbeam.demofragmentviewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCounter extends Fragment {

    public static FragmentCounter newInstance(int index) {

        Bundle args = new Bundle();
        args.putInt("index", index);
        FragmentCounter fragment = new FragmentCounter();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        TextView tvCounter = view.findViewById(R.id.tv_counter);

        int indexInArguments = getArguments().getInt("index");

        tvCounter.setText( indexInArguments + "");

        return view;
    }
}
