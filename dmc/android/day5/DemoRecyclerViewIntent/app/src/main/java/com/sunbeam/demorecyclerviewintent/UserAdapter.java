package com.sunbeam.demorecyclerviewintent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemHolder> {
    private final Context mContext;
    private ArrayList<String> mUserList;

    public UserAdapter(Context context, ArrayList<String> data) {
        mUserList = data;
        mContext = context;
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
        final String currentUser = mUserList.get(position);
        holder.mTvName.setText(currentUser);

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityUser.class);

                intent.putExtra("user", currentUser);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null == mUserList) ? 0 : mUserList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public View mContainer;
        public TextView mTvName;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mContainer = itemView;
            mTvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
