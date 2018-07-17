package com.example.user.mymusicplayer.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.example.user.mymusicplayer.Model.Logger;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();

        switch (intent.getAction()) {
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                Log.d(Logger.getTAG(context), "onReceive: Airplane changed");
                Toast.makeText(context, "Airplane status changed", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_CAMERA_BUTTON:
                Log.d(Logger.getTAG(context), "onReceive: Camera initiated");
                Toast.makeText(context, "Camera initiated", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_HEADSET_PLUG:
                Log.d(Logger.getTAG(context), "onReceive: Headset plugged/unplugged");
                Toast.makeText(context, "Headset changed", Toast.LENGTH_SHORT).show();

            case Intent.ACTION_SCREEN_OFF:
                Log.d(Logger.getTAG(context), "onReceive: Screen off");
                Toast.makeText(context, "Screen off", Toast.LENGTH_SHORT).show();
            case ConnectivityManager.CONNECTIVITY_ACTION:
                Log.d(Logger.getTAG(context), "onReceive: Connectivity changed");
                Toast.makeText(context, "Wifi changed", Toast.LENGTH_SHORT).show();
        }
    }
}
