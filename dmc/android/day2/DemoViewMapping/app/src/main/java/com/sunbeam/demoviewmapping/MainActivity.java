package com.sunbeam.demoviewmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etpassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etpassword.getText().toString();

                if(email.equals("test@gmail.com") &&
                        password.equals("1234")) {
                    // Show success
                } else {
                    // show error
                }
            }
        });



    }
}
