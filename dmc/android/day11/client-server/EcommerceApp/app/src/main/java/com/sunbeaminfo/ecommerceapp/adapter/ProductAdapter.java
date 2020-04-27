package com.sunbeaminfo.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koushikdutta.ion.Ion;
import com.sunbeaminfo.ecommerceapp.R;
import com.sunbeaminfo.ecommerceapp.model.CartItem;
import com.sunbeaminfo.ecommerceapp.model.Product;
import com.sunbeaminfo.ecommerceapp.utils.Utils;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final ActionListener listener;

    public interface ActionListener {
        void onDecrement(int position);
        void onIncrement(int position);
    }

    private final Context context;
    private final ArrayList<CartItem> products;


    public ProductAdapter(Context context, ArrayList<CartItem> products, ActionListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_item_product, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CartItem product = products.get(position);

        // http://<ip>:4000/<tv_1.png>
        String url = Utils.createUrl(product.getThumbnail());

        Ion.with(this.context)
            .load(url)
            .withBitmap()
            .intoImageView(holder.imageView);

        holder.textTitle.setText(product.getTitle());
        holder.textQuantity.setText("" + product.getQuantity());
        holder.textPrice.setText("₹ " + product.getPrice());

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIncrement(position);
            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDecrement(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textTitle, textPrice, textQuantity;

        ImageButton buttonPlus, buttonMinus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textQuantity = itemView.findViewById(R.id.textQuantity);

            buttonPlus = itemView.findViewById(R.id.buttonPlus);
            buttonMinus = itemView.findViewById(R.id.buttonMinus);
        }
    }
}
