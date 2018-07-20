package com.example.user.acronymsapp.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import com.example.user.acronymsapp.model.APIResult;
import com.example.user.acronymsapp.model.Lf;

import java.util.ArrayList;
import java.util.List;

public class HandlerUtils {
    public static HandlerUtils instance = null;
    private static final String STRING_KEY = "definitions";
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

    public void sendMessage(List<Lf> definitions) {
        Log.d("HandlerUtils.java_TAG", "sendMessage: ");
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STRING_KEY, (ArrayList<? extends Parcelable>) definitions);
        message.setData(bundle);
        Log.d("HandlerUtils.java_TAG", "sendMessage Parcelable: ");
        handler.sendMessage(message);
    }

    public List<Lf> parseMessage(Message message) {
        Log.d("HandlerUtils.java_TAG", "parseMessage: ");
        return message.getData().getParcelableArrayList(STRING_KEY);
    }
}