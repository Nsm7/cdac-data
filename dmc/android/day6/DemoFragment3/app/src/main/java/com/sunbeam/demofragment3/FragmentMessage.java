package com.sunbeam.demofragment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMessage extends Fragment {

    private TextView mTvMessage;

    public static FragmentMessage newInstance(String message) {
        
        Bundle args = new Bundle();
        args.putString("message", message);

        FragmentMessage fragment = new FragmentMessage();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        mTvMessage = view.findViewById(R.id.tv_message);

        String messageInArgs = getArguments().getString("message");

        mTvMessage.setText(messageInArgs);
        return view;
    }
}
