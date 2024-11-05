package com.example.a124lttd04_ngovanduong_2312_contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a124lttd04_ngovanduong_2312_contentprovider.adapter.MessageAdapter;
import com.example.a124lttd04_ngovanduong_2312_contentprovider.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ListView lvContacts;
    private MessageAdapter contactsAdapter;
    private List<Message> contactList;
    private List<String> contactIds; // Danh sách ID liên hệ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        lvContacts = findViewById(R.id.lvContacts);
        contactList = new ArrayList<>();
        contactIds = new ArrayList<>(); // Khởi tạo danh sách ID liên hệ

        // Lấy dữ liệu liên hệ từ ứng dụng danh bạ
        loadContactsFromPhone();

        // Thiết lập adapter với dữ liệu
        contactsAdapter = new MessageAdapter(this, contactList);
        lvContacts.setAdapter(contactsAdapter);

        // Xử lý sự kiện click vào item trong ListView
        lvContacts.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy địa chỉ và ID của liên hệ đã chọn
            String phoneNumber = contactList.get(position).getAddress();
            String contactId = contactIds.get(position); // Lấy ID liên hệ

            // Mở hồ sơ liên hệ trong ứng dụng danh bạ
            openContactProfile(contactId);
        });
    }

    private void loadContactsFromPhone() {
        // Truy vấn liên hệ từ ứng dụng danh bạ
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() > 0) {
            // Lưu tên, số điện thoại và ID vào danh sách
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int idIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID); // ID liên hệ

            if (nameIndex >= 0 && phoneIndex >= 0 && idIndex >= 0) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    String phoneNumber = cursor.getString(phoneIndex);
                    String contactId = cursor.getString(idIndex); // Lưu ID liên hệ
                    // Thêm tên, số điện thoại và ID vào danh sách
                    contactList.add(new Message(phoneNumber, name));
                    contactIds.add(contactId); // Thêm ID vào danh sách ID
                }
            }
            cursor.close();
        } else {
            Log.d("ContactsActivity", "Không có liên hệ.");
        }
    }

    private void openContactProfile(String contactId) {
        // Tạo intent để mở hồ sơ liên hệ
        Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, contactId);
        Intent intent = new Intent(Intent.ACTION_VIEW, contactUri);

        // Kiểm tra xem có ứng dụng nào có thể xử lý intent này không
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ContactsActivity", "Không tìm thấy ứng dụng danh bạ.");
        }
    }
}
