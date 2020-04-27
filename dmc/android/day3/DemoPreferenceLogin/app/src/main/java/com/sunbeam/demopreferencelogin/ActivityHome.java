package com.sunbeam.demopreferencelogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String email = prefs.getString("email", null);

        TextView tvEmail = findViewById(R.id.tv_email);

        tvEmail.setText(email);


        Button btnlogout = findViewById(R.id.btn_logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("email", null);
//                editor.remove("email");
                editor.clear();
                editor.commit();

                Intent intent = new Intent(ActivityHome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
