package com.example.user.geocodingandgeofencing.model.data.location;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.user.geocodingandgeofencing.utils.Constants;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class GeoFencingManager {
    private GeofencingClient mGeofencingClient;
    private List<Geofence> mGeofenceList;

    public GeoFencingManager(Context context) {
        mGeofencingClient = LocationServices.getGeofencingClient(context);
        mGeofenceList = new ArrayList<>();
    }

    @SuppressLint("MissingPermission")
    public void Add(Location location) {

        mGeofenceList.add(new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this
                // geofence.
                .setRequestId("AIzaSyD3fIme6BWF5o3_hGwge-85kOhl4y_1fog")
                .setCircularRegion(
                        location.getLatitude(),
                        location.getLongitude(),
                        Constants.GEOFENCE_RADIUS_IN_METERS
                )
                .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());

        mGeofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener((Executor) this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Geofences added
                        // ...
                    }
                })
                .addOnFailureListener((Executor) this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add geofences
                        // ...
                    }
                });
    }

    private PendingIntent getGeofencePendingIntent() {
        return null;
    }

    private GeofencingRequest getGeofencingRequest() {
        return null;
    }

    public static class GeofenceErrorMessages {

        public static String getErrorString(MyIntentGeoFencingService myIntentGeoFencingService, int errorCode) {
            return null;
        }
    }
}
