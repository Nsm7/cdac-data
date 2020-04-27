package com.sunbeam.demodialog3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDialog = findViewById(R.id.tv_dialog);

        tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View dialogView = getLayoutInflater().inflate(R.layout.dialog_demo, null);
                final EditText etMessage = dialogView.findViewById(R.id.et_message);

                AlertDialog.Builder builder = new AlertDialog
                                                .Builder(MainActivity.this)
                                                .setView(dialogView)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        String message = etMessage.getText().toString();
                                                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                mDialog = builder.create();
                mDialog.show();
            }
        });
    }

    @Override
    protected void onPause() {
        if(null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        super.onPause();
    }
}
