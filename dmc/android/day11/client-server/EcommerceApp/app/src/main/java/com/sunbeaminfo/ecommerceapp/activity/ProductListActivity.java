package com.sunbeaminfo.ecommerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.adapter.ProductAdapter;
import com.sunbeaminfo.ecommerceapp.adapter.ProductGridAdapter;
import com.sunbeaminfo.ecommerceapp.model.Product;
import com.sunbeaminfo.ecommerceapp.utils.Constants;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements ProductGridAdapter.ActionListener {

    RecyclerView recyclerView;
    ProductGridAdapter adapter;
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProductGridAdapter(this, products, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuRefresh) {
            loadProducts();
        } else if (item.getItemId() == R.id.menuSignout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit()
                    .putInt("id", 0)
                    .putString("full_name", "")
                    .putString("email", "")
                    .putBoolean("login_status", false)
                    .commit();

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        } else if (item.getItemId() == R.id.menuCart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
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

    @Override
    public void onClick(int position) {
        Product product = products.get(position);

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}
