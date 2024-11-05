package com.example.a124lttd04_ngovanduong_2312_contentprovider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 1001;
    private static final int REQUEST_READ_CALL_LOG = 1002;
    private static final int REQUEST_READ_SMS = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các nút
        Button btnReadMessages = findViewById(R.id.btnReadMessages);
        Button btnCallLog = findViewById(R.id.btnCallLog);
        Button btnContacts = findViewById(R.id.btnContacts);

        // Xử lý sự kiện nhấn nút
        btnReadMessages.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReadMessagesActivity.class);
            startActivity(intent);
        });

        btnCallLog.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CallLogActivity.class);
            startActivity(intent);
        });

        btnContacts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
            startActivity(intent);
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_READ_CALL_LOG);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_READ_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Quyền READ_CONTACTS đã được cấp
                    Toast.makeText(this, "Quyền đọc danh bạ đã được cấp!", Toast.LENGTH_SHORT).show();
                } else {
                    // Quyền READ_CONTACTS bị từ chối
                    Toast.makeText(this, "Quyền đọc danh bạ bị từ chối!", Toast.LENGTH_SHORT).show();
                    // Có thể nhắc nhở hoặc hướng dẫn người dùng cấp quyền
                }
                break;

            case REQUEST_READ_CALL_LOG:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Quyền READ_CALL_LOG đã được cấp
                    Toast.makeText(this, "Quyền đọc lịch sử cuộc gọi đã được cấp!", Toast.LENGTH_SHORT).show();
                } else {
                    // Quyền READ_CALL_LOG bị từ chối
                    Toast.makeText(this, "Quyền đọc lịch sử cuộc gọi bị từ chối!", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_READ_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Quyền READ_SMS đã được cấp
                    Toast.makeText(this, "Quyền đọc tin nhắn đã được cấp!", Toast.LENGTH_SHORT).show();
                } else {
                    // Quyền READ_SMS bị từ chối
                    Toast.makeText(this, "Quyền đọc tin nhắn bị từ chối!", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                // Xử lý các trường hợp quyền khác nếu có
                break;
        }
    }

}
