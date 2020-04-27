package com.sunbeaminfo.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.model.Product;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

import java.util.ArrayList;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ViewHolder> {

    public interface ActionListener {
        void onClick(int position);
    }

    private final Context context;
    private final ArrayList<Product> products;
    private final ActionListener listener;

    public ProductGridAdapter(Context context, ArrayList<Product> products, ActionListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductGridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_item_product_grid, null);
        return new ProductGridAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGridAdapter.ViewHolder holder, final int position) {
        Product product = products.get(position);

        // http://<ip>:4000/<tv_1.png>
        String url = Utils.createUrl(product.getThumbnail());

        Ion.with(this.context)
                .load(url)
                .withBitmap()
                .intoImageView(holder.imageView);

        holder.textTitle.setText(product.getTitle());
        holder.textPrice.setText("₹ " + product.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;

        ImageView imageView;

        TextView textTitle, textPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            imageView = itemView.findViewById(R.id.imageView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }
}
