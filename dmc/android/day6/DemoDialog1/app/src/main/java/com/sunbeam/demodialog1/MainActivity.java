package com.sunbeam.demodialog1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDialog = findViewById(R.id.tv_dialog);

        tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog
                                                .Builder(MainActivity.this)
                                                .setTitle("Title for dialog")
                                                .setMessage("This is the dialog body.");

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
    }
}
