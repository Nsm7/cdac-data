package com.sunbeaminfo.application4.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.constants.Constants;
import com.sunbeaminfo.application4.model.Car;
import com.sunbeaminfo.application4.model.Mobile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileActivity extends AppCompatActivity {

    ArrayList<Mobile> mobiles = new ArrayList<>();
    ArrayAdapter<Mobile> adapter;

    @BindView(R.id.editModel) EditText editModel;
    @BindView(R.id.editCompany) EditText editCompany;
    @BindView(R.id.listView) ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        ButterKnife.bind(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mobiles);
        listView.setAdapter(adapter);
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
            resolver.insert(Constants.CONTENT_URI_MOBILE, values);
        }

    }

    public void onQuery(View v) {
        mobiles.clear();

        String columns[] = {"id", "model", "company"};
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Constants.CONTENT_URI_MOBILE, columns, null, null, null);

        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                Mobile mobile = new Mobile();
                mobile.setId(cursor.getInt(cursor.getColumnIndex("id")));
                mobile.setModel(cursor.getString(cursor.getColumnIndex("model")));
                mobile.setCompany(cursor.getString(cursor.getColumnIndex("company")));

                mobiles.add(mobile);

                cursor.moveToNext();
            }
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }
}
