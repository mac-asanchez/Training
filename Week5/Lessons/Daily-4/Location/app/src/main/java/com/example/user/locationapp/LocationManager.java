package com.example.user.locationapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationManager extends LocationCallback {
    Context context;
    FusedLocationProviderClient client;
    Callback callback;
    private boolean requestLocationUpdates;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    public LocationManager(Context context) {
        this.context = context;
        client = LocationServices.getFusedLocationProviderClient(context);
        callback = (Callback) context;
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        client.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        callback.onLocationResults(location);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onLocationResults(null);
                    }
                });
    }

    public void getLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(context);

        settingsClient.checkLocationSettings(builder.build())
                .addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        requestLocationUpdates = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        requestLocationUpdates = false;
                    }
                });
    }

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        getLocationUpdates();

        if (true) {
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    for (Location location : locationResult.getLocations()) {
                        callback.onLocationResults(location);
                    }
                }
            };
            client.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    public void stopLocationUpdates() {
        client.removeLocationUpdates(locationCallback);
    }

    public interface Callback {
        void onLocationResults(Location location);
    }
}
