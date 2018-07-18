package com.example.user.makingrestcalls.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerCallBack implements Handler.Callback {
    Context context;
    TextView tvResult;

    public HandlerCallBack(Context context, TextView tvResult) {
        this.context = context;
        this.tvResult = tvResult;
    }

    @Override
    public boolean handleMessage(Message message) {
        String result = HandlerUtils.getDefault().parseMessage(message);
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        tvResult.setText(result);
        return false;
    }
}
