package com.example.a124lttd04_ngovanduong_2312_contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a124lttd04_ngovanduong_2312_contentprovider.adapter.MessageAdapter;
import com.example.a124lttd04_ngovanduong_2312_contentprovider.model.Message;

import java.util.ArrayList;
import java.util.List;

public class CallLogActivity extends AppCompatActivity {

    private ListView lvCallLogs;
    private MessageAdapter callLogAdapter;
    private List<Message> callLogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        lvCallLogs = findViewById(R.id.lvCallLogs);
        callLogList = new ArrayList<>();

        // Lấy dữ liệu lịch sử cuộc gọi từ ứng dụng gọi điện
        loadCallLogsFromPhone();

        // Thiết lập adapter với dữ liệu
        callLogAdapter = new MessageAdapter(this, callLogList);
        lvCallLogs.setAdapter(callLogAdapter);

        // Xử lý sự kiện click vào item trong ListView
        lvCallLogs.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy số điện thoại của cuộc gọi đã chọn
            Message selectedCallLog = callLogList.get(position);
            // Mở ứng dụng gọi điện
            openCallLogApp(selectedCallLog.getAddress());
        });
    }

    private void loadCallLogsFromPhone() {
        // Truy vấn lịch sử cuộc gọi từ ứng dụng gọi điện
        Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() > 0) {
            // Lưu số điện thoại vào danh sách
            int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            if (numberIndex >= 0) {
                while (cursor.moveToNext()) {
                    String phoneNumber = cursor.getString(numberIndex);
                    // Thêm số điện thoại vào danh sách
                    callLogList.add(new Message(phoneNumber, ""));
                }
            }
            cursor.close();
        } else {
            Log.d("CallLogActivity", "Không có lịch sử cuộc gọi.");
        }
    }

    private void openCallLogApp(String phoneNumber) {
        // Tạo intent mở ứng dụng gọi điện với số điện thoại đã chọn
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber)); // Gọi đến số điện thoại

        // Kiểm tra xem có ứng dụng nào có thể xử lý intent này không
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("CallLogActivity", "Không tìm thấy ứng dụng gọi điện.");
        }
    }
}
