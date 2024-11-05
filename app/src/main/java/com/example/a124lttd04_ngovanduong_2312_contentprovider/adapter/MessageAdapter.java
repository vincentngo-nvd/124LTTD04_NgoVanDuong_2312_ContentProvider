// MessageAdapter.java
package com.example.a124lttd04_ngovanduong_2312_contentprovider.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import com.example.a124lttd04_ngovanduong_2312_contentprovider.R;
import com.example.a124lttd04_ngovanduong_2312_contentprovider.model.Message; // Import lớp Message

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, List<Message> messages) {
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        Message message = getItem(position);

        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        TextView tvBody = convertView.findViewById(R.id.tvBody);

        tvAddress.setText("From: " + message.getAddress());
        tvBody.setText("Message: " + message.getBody());

        return convertView;
    }
}
