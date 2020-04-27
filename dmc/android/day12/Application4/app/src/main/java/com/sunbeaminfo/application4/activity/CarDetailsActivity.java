package com.sunbeaminfo.application4.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.constants.Constants;
import com.sunbeaminfo.application4.model.Car;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarDetailsActivity extends AppCompatActivity {

    @BindView(R.id.textId) TextView textId;
    @BindView(R.id.textModel) TextView textModel;
    @BindView(R.id.textCompany) TextView textCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int carId = intent.getIntExtra("id", 0);

        // content://com.sunbeaminfo.application4/car/1
        Uri uri = Uri.parse(Constants.SCHEME + Constants.AUTHORITY + "/" + Constants.PATH_CAR + "/" + carId);

        String columns[] = {"id", "model", "company"};
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, columns, null, null, null);
        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            textId.setText("Id: " + cursor.getInt(cursor.getColumnIndex("id")));
            textModel.setText("Model: " + cursor.getString(cursor.getColumnIndex("model")));
            textCompany.setText("Company: " + cursor.getString(cursor.getColumnIndex("company")));
        }
    }

    public void onBack(View v) {
        finish();
    }
}
