package com.sunbeam.demologinsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        final TextView tvMessage = findViewById(R.id.tv_message);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email.equals("test@gmail.com") && password.equals("1234")) {
                    tvMessage.setText(R.string.login_successful);
                    etEmail.setText("");
                    etPassword.setText("");
                } else {
                    tvMessage.setText(R.string.login_failed);
                }
            }
        };

        btnLogin.setOnClickListener(listener);

    }
}
