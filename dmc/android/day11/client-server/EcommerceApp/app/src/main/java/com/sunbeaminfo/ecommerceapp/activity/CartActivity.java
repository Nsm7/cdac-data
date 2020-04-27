package com.sunbeaminfo.ecommerceapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.adapter.ProductAdapter;
import com.sunbeaminfo.ecommerceapp.model.CartItem;
import com.sunbeaminfo.ecommerceapp.utils.Constants;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends BaseActivity implements ProductAdapter.ActionListener {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    ArrayList<CartItem> products = new ArrayList<>();
    @BindView(R.id.textViewPrice) TextView textViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ProductAdapter(this, products, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void updatePrice() {
        float price = 0;
        for (CartItem item : products) {
            price += (item.getPrice() * item.getQuantity());
        }

        textViewPrice.setText("₹ " + price);
    }

    private void loadProducts() {
        products.clear();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int id = preferences.getInt("id", 0);

        String url = Utils.createUrl(Constants.ROUTE_CART_ITEMS + id);
        Log.e("CartActivity", "url: " + url);

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

                                int productId = object.get("productId").getAsInt();
                                int cartId = object.get("cartId").getAsInt();
                                String title = object.get("title").getAsString();
                                String description = object.get("description").getAsString();
                                String thumbnail = object.get("thumbnail").getAsString();
                                float price = object.get("price").getAsFloat();
                                int categoryId = object.get("categoryId").getAsInt();
                                int quantity = object.get("quantity").getAsInt();

                                products.add(new CartItem(productId, cartId, title, description, thumbnail, price, categoryId, quantity));
                            }

                            adapter.notifyDataSetChanged();

                            updatePrice();
                        }

                    }

                });

    }

    @Override
    public void onDecrement(int position) {
        CartItem item = products.get(position);
        int quantity = item.getQuantity() - 1;

        if (quantity == 0) {
            removeProductFromCart(item.getCartId());
        } else {
            updateQuantity(item.getCartId(), quantity);
        }
    }

    @Override
    public void onIncrement(int position) {
        CartItem item = products.get(position);
        int quantity = item.getQuantity() + 1;

        updateQuantity(item.getCartId(), quantity);
    }

    private void removeProductFromCart(int cartItemId) {
        String url = Utils.createUrl(Constants.ROUTE_CART + cartItemId);

        Ion.with(this)
                .load("DELETE", url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String status = result.get("status").getAsString();
                        if (status.equals("success")) {
                            Toast.makeText(CartActivity.this, "Successfully removed product", Toast.LENGTH_SHORT).show();

                            loadProducts();
                        }

                    }
                });
    }

    private void updateQuantity(int cartItemId, int quantity) {
        String url = Utils.createUrl(Constants.ROUTE_CART + cartItemId);
        JsonObject body = new JsonObject();
        body.addProperty("quantity", quantity);

        Ion.with(this)
                .load("PUT", url)
                .setJsonObjectBody(body)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String status = result.get("status").getAsString();
                        if (status.equals("success")) {
                            Toast.makeText(CartActivity.this, "Successfully updated quantity", Toast.LENGTH_SHORT).show();

                            loadProducts();
                        }

                    }
                });
    }
}
