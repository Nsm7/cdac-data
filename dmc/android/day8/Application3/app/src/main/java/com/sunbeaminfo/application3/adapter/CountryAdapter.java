package com.sunbeaminfo.application3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunbeaminfo.application3.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    Context context;
    ArrayList<String> countries;

    public CountryAdapter(Context context, ArrayList<String> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // inflate xml file
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_item_country, null);

        ViewHolder holder = new ViewHolder(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String country = countries.get(position);
        holder.textName.setText(country);
    }

    @Override
    public int getItemCount() {
        return this.countries.size();
    }

    // holds view for every recycler item
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the textName
            textName = itemView.findViewById(R.id.textName);
        }
    }
}
