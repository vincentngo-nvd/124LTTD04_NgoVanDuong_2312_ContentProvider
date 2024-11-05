package com.example.a124lttd04_ngovanduong_2312_contentprovider.model;

public class CallLogItem {
    private String number;
    private String duration;

    public CallLogItem(String number, String duration) {
        this.number = number;
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public String getDuration() {
        return duration;
    }
}
