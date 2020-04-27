package com.sunbeam.demorecyclerview3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    private ArrayList<User> mListData;

    public ListAdapter(ArrayList<User> data) {
        mListData = data;
    }

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
        User currentUser = mListData.get(position);
        holder.mTvName.setText(currentUser.getName());
        holder.mTvAddress.setText(currentUser.getAddress());

        holder.mRowView.setBackgroundResource(position % 2 == 0 ? R.color.colorPrimary
                                                    : R.color.colorPrimaryDark);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public View mRowView;
        public TextView mTvName;
        public TextView mTvAddress;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mRowView = itemView;
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}
