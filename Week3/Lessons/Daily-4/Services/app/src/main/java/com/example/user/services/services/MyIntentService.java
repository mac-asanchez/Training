package com.example.user.services.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName() + "_TAG";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        Log.d(TAG, "onHandleIntent: Thread: " + Thread.currentThread().getName());

        Log.d(TAG, "onHandleIntent: Task starting");

        for (int i = 0; i < 4; i++) {
            try{
                Thread.sleep(500);
                Log.d(TAG, "onHandleIntent: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
