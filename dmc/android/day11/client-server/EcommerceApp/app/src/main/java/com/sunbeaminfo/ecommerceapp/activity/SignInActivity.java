package com.sunbeaminfo.ecommerceapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.utils.Constants;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.editEmail) TextInputEditText editEmail;
    @BindView(R.id.editPassword) TextInputEditText editPassword;
    @BindView(R.id.checkBoxRememberme) CheckBox checkBoxRememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    public void onSignup(View v) {

    }

    public void onSignin(View v) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (email.length() == 0) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        } else {

            // create body
            JsonObject body = new JsonObject();
            body.addProperty("email", email);
            body.addProperty("password", password);

            // send POST /user/signin HTTP request
            String url = Utils.createUrl(Constants.ROUTE_USER_SIGNIN);
            Log.e("SigninActivity", "url: " + url);

            Ion.with(this)
                    .load("POST", url)
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            String status = result.get("status").getAsString();
                            if (status.equals("success")) {

                                // get the user details
                                JsonObject object = result.get("data").getAsJsonObject();

                                // save the last login
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SignInActivity.this);
                                preferences.edit()
                                    .putInt("id", object.get("id").getAsInt())
                                    .putString("full_name", object.get("full_name").getAsString())
                                    .putString("email", object.get("email").getAsString())
                                    .putBoolean("login_status", true)
                                    .commit();

                                Intent intent = new Intent(SignInActivity.this, ProductListActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
}
