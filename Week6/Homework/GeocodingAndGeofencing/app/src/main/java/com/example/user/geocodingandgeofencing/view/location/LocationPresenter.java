package com.example.user.geocodingandgeofencing.view.location;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.text.Editable;
import android.util.Log;

import com.example.user.geocodingandgeofencing.model.data.location.LocationManager;
import com.example.user.geocodingandgeofencing.model.permission.PermissionManager;

import java.io.IOException;

import javax.inject.Inject;

public class LocationPresenter implements LocationContract.Presenter {
    private static final String TAG = LocationPresenter.class.getSimpleName() + "_TAG";
    LocationContract.View view;
    PermissionManager permissionManager;
    LocationManager locationManager;

    public LocationPresenter(Context context) {
        Log.d(TAG, "LocationPresenter: ");
        permissionManager = new PermissionManager(context);
        permissionManager.setPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager = new LocationManager(context);
    }

    @Override
    public void attachView(LocationContract.View view) {
        Log.d(TAG, "attachView: ");
        this.view = view;
    }

    @Override
    public void detachView() {
        Log.d(TAG, "detachView: ");
        this.view = null;
    }

    @Override
    public void checkPermission() {
        Log.d(TAG, "checkPermission: ");
        permissionManager.checkPermission(new PermissionManager.Callback() {
            @Override
            public void onPermissionResults(int requestCode, boolean isGranted) {
                Log.d(TAG, "onPermissionResults: ");
                view.onPermissionResult(isGranted);
            }
        });
    }

    @Override
    public void getCurrentLocation() {
        Log.d(TAG, "getCurrentLocation: ");
        locationManager.getCurrentLocation(new LocationManager.Callback() {
            @Override
            public void onLocationResults(Location location, Address address) {
                Log.d(TAG, "onLocationResults: ");
                view.onLocationResult(location, address);
            }
        });
    }

    @Override
    public void changeCurrentLocation(String strAddress) {
        Log.d(TAG, "changeCurrentLocation: ");
        try {
            locationManager.getNewLocation(strAddress, new LocationManager.Callback() {
                @Override
                public void onLocationResults(Location location, Address address) {
                    Log.d(TAG, "onLocationResults: New Location");
                    view.onLocationResult(location, address);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
