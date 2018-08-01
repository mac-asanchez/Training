package com.example.user.encryption;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.user.encryption.di.component.DaggerAppComponent;
import com.example.user.encryption.di.component.MainComponent;
import com.example.user.encryption.di.module.EncryptionModule;
import com.example.user.encryption.di.module.MainModule;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName() + "_TAG";
    MainComponent mainComponent;

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    public MainComponent getMainComponent() {
        mainComponent = (MainComponent) DaggerAppComponent.builder()
                .encryptionModule(new EncryptionModule(this))
                .build().addMain(new MainModule());
        return mainComponent;
    }

    public void clearMainComponent() {
        mainComponent = null;
    }
}
