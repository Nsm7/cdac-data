package com.sunbeaminfo.application4.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.constants.Constants;
import com.sunbeaminfo.application4.model.Car;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarActivity extends AppCompatActivity {

    ArrayList<Car> cars = new ArrayList<>();
    ArrayAdapter<Car> adapter;

    @BindView(R.id.editModel) EditText editModel;
    @BindView(R.id.editCompany) EditText editCompany;
    @BindView(R.id.listView) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cars);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = cars.get(position);
                Intent intent = new Intent(CarActivity.this, CarDetailsActivity.class);
                intent.putExtra("id", car.getId());
                startActivity(intent);
            }
        });
    }

    public void onInsert(View v) {
        String model = editModel.getText().toString();
        String company = editCompany.getText().toString();

        if (model.length() == 0) {
            Toast.makeText(this, "Enter model", Toast.LENGTH_SHORT).show();
        } else if (company.length() == 0) {
            Toast.makeText(this, "Enter company", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put("model", model);
            values.put("company", company);

            ContentResolver resolver = getContentResolver();
            resolver.insert(Constants.CONTENT_URI_CAR, values);
        }

    }

    public void onQuery(View v) {
        cars.clear();

        String columns[] = {"id", "model", "company"};
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Constants.CONTENT_URI_CAR, columns, null, null, null);

        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                Car car = new Car();
                car.setId(cursor.getInt(cursor.getColumnIndex("id")));
                car.setModel(cursor.getString(cursor.getColumnIndex("model")));
                car.setCompany(cursor.getString(cursor.getColumnIndex("company")));

                cars.add(car);

                cursor.moveToNext();
            }
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
