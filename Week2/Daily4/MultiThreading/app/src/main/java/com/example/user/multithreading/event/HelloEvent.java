package com.example.user.multithreading.event;

public class HelloEvent {
    String data;

    public HelloEvent(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
