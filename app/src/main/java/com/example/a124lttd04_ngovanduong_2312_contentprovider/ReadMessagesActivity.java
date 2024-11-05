package com.example.a124lttd04_ngovanduong_2312_contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a124lttd04_ngovanduong_2312_contentprovider.adapter.MessageAdapter;
import com.example.a124lttd04_ngovanduong_2312_contentprovider.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ReadMessagesActivity extends AppCompatActivity {

    private ListView lvMessages;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_messages);

        lvMessages = findViewById(R.id.lvMessages);
        messageList = new ArrayList<>();

        // Lấy dữ liệu tin nhắn từ ứng dụng nhắn tin
        loadMessagesFromSMSApp();

        // Thiết lập adapter với dữ liệu
        messageAdapter = new MessageAdapter(this, messageList);
        lvMessages.setAdapter(messageAdapter);

        // Xử lý sự kiện click vào item trong ListView
        lvMessages.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy địa chỉ của tin nhắn đã chọn
            Message selectedMessage = messageList.get(position);
            // Mở ứng dụng nhắn tin
            openMessagingApp(selectedMessage.getAddress());
        });
    }

    private void loadMessagesFromSMSApp() {
        // Truy vấn tin nhắn từ ứng dụng nhắn tin
        Cursor cursor = getContentResolver().query(
                Telephony.Sms.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() > 0) {
            // Chỉ cần lưu địa chỉ tin nhắn vào danh sách
            int addressIndex = cursor.getColumnIndex(Telephony.Sms.ADDRESS);
            if (addressIndex >= 0) {
                while (cursor.moveToNext()) {
                    String address = cursor.getString(addressIndex);
                    // Thêm địa chỉ vào danh sách
                    messageList.add(new Message(address, ""));
                }
            }
            cursor.close();
        } else {
            Log.d("ReadMessagesActivity", "Không có tin nhắn.");
        }
    }

    private void openMessagingApp(String address) {
        // Tạo intent mở ứng dụng nhắn tin với địa chỉ đã chọn
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + address)); // Chỉ định địa chỉ nhận tin nhắn

        // Kiểm tra xem có ứng dụng nào có thể xử lý intent này không
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ReadMessagesActivity", "Không tìm thấy ứng dụng nhắn tin.");
        }
    }
}
