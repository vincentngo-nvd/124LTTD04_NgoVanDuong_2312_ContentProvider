// Message.java
package com.example.a124lttd04_ngovanduong_2312_contentprovider.model;

public class Message {
    private String address;
    private String body;

    public Message(String address, String body) {
        this.address = address;
        this.body = body;
    }

    public String getAddress() {
        return address;
    }

    public String getBody() {
        return body;
    }
}
