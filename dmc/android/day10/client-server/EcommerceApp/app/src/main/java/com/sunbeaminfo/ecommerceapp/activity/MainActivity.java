package com.sunbeaminfo.ecommerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.adapter.ProductAdapter;
import com.sunbeaminfo.ecommerceapp.model.Product;
import com.sunbeaminfo.ecommerceapp.utils.Constants;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuRefresh) {
            loadProducts();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProducts() {
        products.clear();

        String url = Utils.createUrl(Constants.ROUTE_PRODUCT);

        // send GET HTTP request
        Ion.with(this)
            .load("GET", url)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject response) {

                    String status = response.get("status").getAsString();
                    if (status.equals("success")) {

                        // get the products
                        JsonArray array = response.get("data").getAsJsonArray();
                        for (int index = 0; index < array.size(); index++) {
                            JsonObject object = array.get(index).getAsJsonObject();

                            int id = object.get("id").getAsInt();
                            String title = object.get("title").getAsString();
                            String description = object.get("description").getAsString();
                            String thumbnail = object.get("thumbnail").getAsString();
                            float price = object.get("price").getAsFloat();
                            int categoryId = object.get("categoryId").getAsInt();

                            products.add(new Product(id, title, description, thumbnail, price, categoryId));
                        }

                        adapter.notifyDataSetChanged();
                    }

                }

            });

    }
}
