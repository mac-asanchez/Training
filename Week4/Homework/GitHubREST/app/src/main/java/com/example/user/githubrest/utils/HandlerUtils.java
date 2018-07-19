package com.example.user.githubrest.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import com.example.user.githubrest.model.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

public class HandlerUtils {
    public static final String TAG = HandlerUtils.class.getSimpleName() + "_TAG";
    public static HandlerUtils instance = null;
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

    public void sendMessage(Object data) {
        Message message = new Message();
        Bundle bundle = new Bundle();

        if (data instanceof Parcelable) {
            bundle.putParcelable(Constants.GITHUB_PROFILE, (Parcelable) data);
        }

        message.setData(bundle);
        handler.sendMessage(message);
    }

    public void sendListMessage(List<GitHubRepo> data) {
        Log.d(TAG, "sendListMessage: " + data.size());
        Message message = new Message();
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList(Constants.GITHUB_REPOS, (ArrayList<? extends Parcelable>) data);

        message.setData(bundle);
        handler.sendMessage(message);
    }

    public Object parseMessage(Message message) {
        return message.getData().getParcelable(Constants.GITHUB_PROFILE);
    }

    public List<GitHubRepo> parseListMessage(Message message) {
        Log.d(TAG, "parseListMessage: ");
        return message.getData().getParcelableArrayList(Constants.GITHUB_REPOS);
    }
}
