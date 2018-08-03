package com.example.user.analyticsapp;

import android.content.Context;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class AnalyticsManager {
    private static final String TOKEN = "ecb221e1ffccf6496358616dfb01699e";
    MixpanelAPI mixpanelAPI;

    public AnalyticsManager(Context context) {
        mixpanelAPI = MixpanelAPI.getInstance(context, TOKEN);
    }

    public void trackEvent(Event event) {
        if (event.getName().equals(EventType.LOAD_IMAGE)) {
            mixpanelAPI.track(event.getName());
        } else {
            mixpanelAPI.track(event.getName(), event.getProps());
        }
    }

    public void timeEvent(Event event) {
        mixpanelAPI.timeEvent(event.getName());
    }

    public static class EventType {
        public static final String RUN = "run";
        public static final String SWIM = "swim";
        public static final String SKYDIVE = "skydive";
        public static final String JUMP = "jump";
        public static final String LOAD_IMAGE = "load_image";
    }
}
