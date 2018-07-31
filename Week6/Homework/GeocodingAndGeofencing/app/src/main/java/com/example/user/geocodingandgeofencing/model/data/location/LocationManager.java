package com.example.user.geocodingandgeofencing.model.data.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

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

import java.io.IOException;
import java.util.List;

public class LocationManager extends LocationCallback {
    private static final String TAG = LocationManager.class.getSimpleName() + "_TAG";
    Context context;
    FusedLocationProviderClient client;
    private boolean requestLocationUpdates;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Geocoder geocoder;

    public LocationManager(Context context) {
        this.context = context;
        client = LocationServices.getFusedLocationProviderClient(context);
        geocoder = new Geocoder(context);
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation(final Callback callback) {
        Log.d(TAG, "getCurrentLocation: ");
        client.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        try {
                            callback.onLocationResults(location, getAddressFromLocation(location));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onLocationResults(null, null);
                    }
                });
    }

    public void getLocationUpdates() {
        Log.d(TAG, "getLocationUpdates: ");
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
    public void startLocationUpdates(final Callback callback) {
        Log.d(TAG, "startLocationUpdates: ");
        getLocationUpdates();

        if (true) {
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Log.d(TAG, "onLocationResult: ");
                    for (Location location : locationResult.getLocations()) {
                        try {
                            callback.onLocationResults(location, getAddressFromLocation(location));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            client.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    public void stopLocationUpdates() {
        Log.d(TAG, "stopLocationUpdates: ");
        client.removeLocationUpdates(locationCallback);
    }

    private Address getAddressFromLocation(Location location) throws IOException {
        Log.d(TAG, "getAddressFromLocation: ");
        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

        return addressList.get(0);
    }

    private Location getLocationFromAddress(Address address) {
        Log.d(TAG, "getLocationFromAddress: ");
        Location location = new Location("");//provider name is unnecessary
        location.setLatitude(address.getLatitude());//your coords of course
        location.setLongitude(address.getLongitude());

        return location;
    }

    public void getNewLocation(String strAddress, final Callback callback) throws IOException {
        Log.d(TAG, "getNewLocation: ");
        List<Address> addressList = geocoder.getFromLocationName(strAddress, 1);
        callback.onLocationResults(getLocationFromAddress(addressList.get(0)), addressList.get(0));
    }

    public interface Callback {
        void onLocationResults(Location location, Address address);
    }
}
