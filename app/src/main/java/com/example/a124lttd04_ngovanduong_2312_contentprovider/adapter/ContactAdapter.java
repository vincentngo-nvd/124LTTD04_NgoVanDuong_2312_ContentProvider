package com.example.a124lttd04_ngovanduong_2312_contentprovider.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a124lttd04_ngovanduong_2312_contentprovider.R;
import com.example.a124lttd04_ngovanduong_2312_contentprovider.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        Contact contact = getItem(position);

        TextView tvContactName = convertView.findViewById(R.id.tvContactName);
        TextView tvContactNumber = convertView.findViewById(R.id.tvContactNumber);

        tvContactName.setText("Name: " + contact.getName());
        tvContactNumber.setText("Phone: " + contact.getNumber());

        return convertView;
    }
}
