package com.sunbeam.demofragmentviewpger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPagerItem extends Fragment {

    public static FragmentPagerItem newInstance(int index) {
        
        Bundle args = new Bundle();
        args.putInt("index", index);
        FragmentPagerItem fragment = new FragmentPagerItem();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_item, container, false);

        TextView tvIndex = view.findViewById(R.id.tv_index);

        int index = getArguments().getInt("index");

        tvIndex.setText(index + "");

        return view;
    }
}
