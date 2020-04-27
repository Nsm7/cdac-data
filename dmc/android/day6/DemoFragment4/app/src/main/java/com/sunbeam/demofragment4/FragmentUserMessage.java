package com.sunbeam.demofragment4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentUserMessage extends Fragment {

    private ActivityCallbacks mCallbackInterface;

    public static FragmentUserMessage newInstance() {
        
        FragmentUserMessage fragment = new FragmentUserMessage();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_message, container, false);

        final EditText etMessage = view.findViewById(R.id.et_message);
        Button btnMessage = view.findViewById(R.id.btn_message);


        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToUpdate = etMessage.getText().toString();

                if(null != mCallbackInterface) {
                    mCallbackInterface.setMessage(messageToUpdate);
                }
            }
        });


        return view;
    }

    public void setCallbackInterface(ActivityCallbacks interfaceInstance) {
        mCallbackInterface = interfaceInstance;
    }

    interface ActivityCallbacks {
        void setMessage(String message);
    }
}
