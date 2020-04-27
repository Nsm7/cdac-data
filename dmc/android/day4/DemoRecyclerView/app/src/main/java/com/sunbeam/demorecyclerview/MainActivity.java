package com.sunbeam.demorecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvList = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvList.setLayoutManager(layoutManager);

        ListAdapter adapter = new ListAdapter();
        rvList.setAdapter(adapter);
    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View rowView = inflater.inflate(R.layout.list_item_test, parent, false);

            ItemHolder holder = new ItemHolder(rowView);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            int rowData = position;
            holder.mTvCounter.setText(rowData + "");
        }


        @Override
        public int getItemCount() {
            return 50;
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            public TextView mTvCounter;

            public ItemHolder(@NonNull View itemView) {
                super(itemView);
                mTvCounter = itemView.findViewById(R.id.tv_counter);
            }
        }
    }
}
