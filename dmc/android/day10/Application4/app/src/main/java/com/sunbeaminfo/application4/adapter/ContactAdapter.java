package com.sunbeaminfo.application4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunbeaminfo.application4.R;
import com.sunbeaminfo.application4.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public interface ContactAdapterActionListener {
        void onEdit(int position);
        void onDelete(int position);
    }


    Context context;
    ArrayList<Contact> contacts;
    ContactAdapterActionListener listener;

    public ContactAdapter(Context context, ArrayList<Contact> contacts, ContactAdapterActionListener listener) {
        this.context = context;
        this.contacts = contacts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recyclerview_item_contact, null);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Contact contact = contacts.get(position);

        holder.textName.setText(contact.getName());
        holder.textAddress.setText(contact.getAddress());
        holder.textEmail.setText(contact.getEmail());
        holder.textPhone.setText(contact.getPhone());
        holder.textCity.setText(contact.getCity());

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ContactAdapter", "edit " + contact.getName());
                listener.onEdit(position);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ContactAdapter", "delete " + contact.getName());
                listener.onDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textAddress, textEmail, textPhone, textCity;
        ImageButton buttonEdit, buttonDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textAddress = itemView.findViewById(R.id.textAddress);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPhone = itemView.findViewById(R.id.textPhone);
            textCity = itemView.findViewById(R.id.textCity);

            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
