package com.example.user.mystore;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.flurry.android.FlurryAgent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName() + "_TAG";
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        Log.d(TAG, "getRefWatcher: ");
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //region Timber
        Timber.plant(new Timber.DebugTree());
        Timber.tag(TAG).d("onCreate");
        //endregion

        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "R79R3HYFYXBZCK5ZTNWY");

        if (LeakCanary.isInAnalyzerProcess(this)) {
            Timber.tag(TAG).d("isInAnalyzerProcess");
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        /*if (refWatcher == null) {
            Timber.tag(TAG).d("isNotInAnalyzerProcess");

            refWatcher = LeakCanary.install(this);
        }*/

        refWatcher = LeakCanary.install(this);
    }

}
