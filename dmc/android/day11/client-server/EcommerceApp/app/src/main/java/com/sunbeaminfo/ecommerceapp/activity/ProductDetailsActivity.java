package com.sunbeaminfo.ecommerceapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.model.Product;
import com.sunbeaminfo.ecommerceapp.utils.Constants;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

public class ProductDetailsActivity extends BaseActivity {

    TextView textPrice, textDescription;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        textPrice = findViewById(R.id.textPrice);
        textDescription = findViewById(R.id.textDescription);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        // http://<ip>:4000/<tv_1.png>
        String url = Utils.createUrl(product.getThumbnail());

        Ion.with(this)
                .load(url)
                .withBitmap()
                .intoImageView(imageView);

        textDescription.setText(product.getDescription());
        textPrice.setText("₹ " + product.getPrice());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(product.getTitle());
    }

    public void onAddToCart(View v) {
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String url = Utils.createUrl(Constants.ROUTE_CART_ITEMS + preferences.getInt("id", 0));

        JsonObject body = new JsonObject();
        body.addProperty("productId", product.getId());
        body.addProperty("quantity", 1);

        Ion.with(this)
            .load("POST", url)
            .setJsonObjectBody(body)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    String status = result.get("status").getAsString();
                    if (status.equals("success")) {
                        Toast.makeText(ProductDetailsActivity.this, "Successfully added this product to your cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductDetailsActivity.this, result.get("error").getAsJsonObject().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
