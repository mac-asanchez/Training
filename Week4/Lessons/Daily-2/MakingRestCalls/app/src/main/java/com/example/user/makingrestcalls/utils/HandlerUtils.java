package com.example.user.makingrestcalls.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HandlerUtils {
    public static HandlerUtils instance = null;
    private static final String STRING_KEY = "data";
    Handler handler;

    private HandlerUtils(){
        //denies initialization
    }

    public static HandlerUtils getDefault(){
        //Singleton - one instance in all the life cycle
        //volatile
        //region lazy initialization
        if (instance == null) {
            instance = new HandlerUtils();
        }

        return instance;
        //endregion
    }

    public void setOwner(Object object){
        if (object instanceof Handler.Callback) {
            handler = new Handler((Handler.Callback) object);
        }
    }

    public void unregisterOwner() {
        this.handler = null;
    }

    public void sendMessage(String data) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(STRING_KEY, data);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public String parseMessage(Message message) {
        return message.getData().getString(STRING_KEY);
    }
}
