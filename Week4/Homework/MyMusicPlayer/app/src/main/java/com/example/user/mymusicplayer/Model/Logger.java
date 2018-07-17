package com.example.user.mymusicplayer.Model;

import android.content.Context;

public class Logger {
    public static String getTAG(Context ctx){
        return ctx.getClass().getSimpleName() + "_TAG";
    }
}