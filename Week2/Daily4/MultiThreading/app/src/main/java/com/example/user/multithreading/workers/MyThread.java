package com.example.user.multithreading.workers;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.example.user.multithreading.event.HelloEvent;
import com.example.user.multithreading.utils.TaskCreator;

import org.greenrobot.eventbus.EventBus;

public class MyThread extends Thread {
    TextView tvMain;
    Handler handler;

    public MyThread(TextView tvMain){
        this.tvMain = tvMain;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        super.run();

        HelloEvent helloEvent = new HelloEvent("Before: This is the data");

        //EventBus post
        EventBus.getDefault().post(helloEvent);

        //before the task is started
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvMain.setText("Task starting");
            }
        });

        try {
            TaskCreator.createSimpleTask(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                tvMain.setText("Task Completed");
            }
        });

        //EventBus data after
        helloEvent.setData("After: this is new data");
        EventBus.getDefault().post(helloEvent);
    }
}
