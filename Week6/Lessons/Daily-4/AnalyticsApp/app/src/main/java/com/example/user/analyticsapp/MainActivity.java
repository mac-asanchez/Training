package com.example.user.analyticsapp;

import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMain;
    private AnalyticsManager analyticsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMain = findViewById(R.id.ivMain);
        analyticsManager = new AnalyticsManager(this);
    }

    public void onRunning(View view) {
        String EventName = AnalyticsManager.EventType.RUN;
        JSONObject props = new Event.PropertyBuilder()
                .addProp("speed", "5mph")
                .build();
        Event event = new Event(EventName, props);
        analyticsManager.trackEvent(event);
    }

    public void onSwimming(View view) {
        String EventName = AnalyticsManager.EventType.SWIM;
        JSONObject props = new Event.PropertyBuilder()
                .addProp("distance", "100ft")
                .build();
        Event event = new Event(EventName, props);
        analyticsManager.trackEvent(event);
    }

    public void onSkydiving(View view) {
        String EventName = AnalyticsManager.EventType.SKYDIVE;
        JSONObject props = new Event.PropertyBuilder()
                .addProp("altitude", "18000ft")
                .build();
        Event event = new Event(EventName, props);
        analyticsManager.trackEvent(event);
    }

    public void onJumping(View view) {
        String EventName = AnalyticsManager.EventType.JUMP;
        JSONObject props = new Event.PropertyBuilder()
                .addProp("height", "1ft")
                .build();
        Event event = new Event(EventName, props);
        analyticsManager.trackEvent(event);
    }

    public void onImageLoad(View view) {
        analyticsManager.timeEvent(new Event(AnalyticsManager.EventType.LOAD_IMAGE, null));
        Random random = new Random();
        int maxTime = 3000;
        int minTime = 1000;

        int randomeTime = random.nextInt(maxTime + 1 - minTime) + minTime;


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ivMain.setBackground(getDrawable(R.drawable.ic_launcher_background));
                }
            }
        }, 200);

        Event event = new Event(AnalyticsManager.EventType.LOAD_IMAGE,
                new Event.PropertyBuilder().addProp("time", randomeTime)
                        .build());
        analyticsManager.trackEvent(event);
    }
}
