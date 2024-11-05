package com.example.a124lttd04_ngovanduong_2312_contentprovider.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a124lttd04_ngovanduong_2312_contentprovider.R;
import com.example.a124lttd04_ngovanduong_2312_contentprovider.model.CallLogItem;

import java.util.List;

public class CallLogAdapter extends ArrayAdapter<CallLogItem> {

    public CallLogAdapter(Context context, List<CallLogItem> callLogs) {
        super(context, 0, callLogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_call_log, parent, false);
        }

        CallLogItem callLogItem = getItem(position);

        TextView tvNumber = convertView.findViewById(R.id.tvNumber);
        TextView tvDuration = convertView.findViewById(R.id.tvDuration);

        tvNumber.setText("Number: " + callLogItem.getNumber());
        tvDuration.setText("Duration: " + callLogItem.getDuration() + " seconds");

        return convertView;
    }
}
