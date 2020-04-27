package com.sunbeam.demorecyclerview4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemHolder> {
    private ArrayList<User> mList;

    public UserAdapter(ArrayList<User> data) {
        mList = data;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.list_item_user, parent, false);

        ItemHolder holder = new ItemHolder(rowView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        final User currentUser = mList.get(position);

        holder.mTvName.setText(currentUser.getName());
        holder.mTvHandle.setText(currentUser.getHandle());

        if(currentUser.isFollowing()) {
            holder.mBtnFollow.setText("Unfollow");
        } else {
            holder.mBtnFollow.setText("Follow");
        }

        holder.mBtnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser.isFollowing()) {
                    currentUser.setFollowing(false);
                } else {
                    currentUser.setFollowing(true);
                }

//                currentUser.setFollowing(currentUser.isFollowing() ? false : true);

//                currentUser.setFollowing(!currentUser.isFollowing());

                UserAdapter.this.notifyItemChanged(position);

//                UserAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(null == mList) {
            return 0;
        }
        return mList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        public TextView mTvName;
        public TextView mTvHandle;
        public Button mBtnFollow;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvHandle = itemView.findViewById(R.id.tv_handle);
            mBtnFollow = itemView.findViewById(R.id.btn_follow);
        }
    }
}
