package com.example.user.geocodingandgeofencing.model.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionManager {
    public static final int MY_PERMISSION_REQUEST_LOCATION = 100;
    private static final String TAG = PermissionManager.class.getSimpleName() + "_TAG";
    Context context;
    String currentPermission;

    public PermissionManager(Context context) {
        this.context = context;
    }

    public void setPermission(String permission) {
        this.currentPermission = permission;
    }

    public void checkPermission(Callback callback) {
        // Here, thisActivity is the current activity
        Log.d(TAG, "checkPermission: Check permission");
        if (ContextCompat.checkSelfPermission(context, currentPermission) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkPermission: Permission not granted");
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    currentPermission)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.d(TAG, "checkPermission: show explanation");
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{currentPermission},
                        MY_PERMISSION_REQUEST_LOCATION);
                Log.d(TAG, "checkPermission: request the permissions");

                // MY_PERMISSION_REQUEST_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            Log.d(TAG, "checkPermission: Permission Granted");
            callback.onPermissionResults(MY_PERMISSION_REQUEST_LOCATION, true);
        }
    }

    public void checkResults(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG, "checkResults: ");
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "checkResults: Permission granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Log.d(TAG, "checkResults: Permission denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public interface Callback {
        void onPermissionResults(int requestCode, boolean isGranted);
    }
}