package com.example.user.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.services.services.MyBoundService;
import com.example.user.services.services.MyIntentService;
import com.example.user.services.services.MyJobService;
import com.example.user.services.services.MyNewProcessService;
import com.example.user.services.services.MyService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int JOB_NORMAL_SERVICE = 10;

    MyBoundService boundService;
    Boolean isBound = false;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartedServices(View view) {
        //region Intent for Service
        Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
        serviceIntent.putExtra("data", "Data from main activity");
        //endregion

        //region Intent for ServiceIntent
        Intent intIntent = new Intent(getApplicationContext(), MyIntentService.class);
        //endregion

        switch (view.getId()) {
            //region Start Service
            case R.id.btnStartService:
                startService(serviceIntent);
                break;
            //endregion

            //region Stop Service
            case R.id.btnStopService:
                stopService(serviceIntent);
                break;
            //endregion

            //region Start Intent Service
            case R.id.btnStartIntentService:
                startService(intIntent);
                break;
            //endregion
        }
    }

    public void onBoundService(View view) {
        Intent boundIntent = new Intent(getApplicationContext(), MyBoundService.class);

        switch (view.getId()) {
            //region Bound Service
            case R.id.btnBoundService:
                if (!isBound) {
                    bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                    isBound = true;
                }
                break;
            //endregion

            //region Get Data
            case R.id.btnGetData:
                if (isBound) {
                    String data = boundService.getDataFromServer();
                    Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                }
                break;
            //endregion

            //region Unbind
            case R.id.btnUnBind:
                if (isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            //endregion
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onScheduleJob(View view) {
        ComponentName componentName = new ComponentName(getApplicationContext(), MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_NORMAL_SERVICE, componentName)
                /*.setMinimumLatency(1000)*/
                .setOverrideDeadline(5000)
                .setRequiresDeviceIdle(true);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }

    public void onServiceNewProcess(View view) {
        Intent intent = new Intent(getApplicationContext(), MyNewProcessService.class);
        startService(intent);
    }
}
