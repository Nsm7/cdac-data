package com.sunbeam.demopreferencelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String loggedInUserEmail = prefs.getString("email", null);

        if(loggedInUserEmail != null) {
            Intent intent = new Intent(this, ActivityHome.class);
            startActivity(intent);
        }

        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email.equals("sam@gmail.com") && password.equals("1234")) {

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", email);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, ActivityHome.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
