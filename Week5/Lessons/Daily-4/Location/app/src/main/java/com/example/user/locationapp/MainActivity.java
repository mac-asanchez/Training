package com.example.user.locationapp;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PermissionManager.Callback, LocationManager.Callback {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    Location location;
    private LocationManager locationManager;
    private PermissionManager permissionManager;
    private TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionManager = new PermissionManager(this);
        permissionManager.setPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager = new LocationManager(this);

        tvLocation = findViewById(R.id.tvLocation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        permissionManager.checkPermission();
    }

    @Override
    public void onPermissionResults(int requestCode, boolean isGranted) {
        if (isGranted) {
            locationManager.getCurrentLocation();
        } else {

        }
    }

    @Override
    public void onLocationResults(Location location) {
        Log.d(TAG, "onLocationResults: " + location.toString());
        String latitude = String.valueOf(location.getLatitude());
        String altitude = String.valueOf(location.getAltitude());
        tvLocation.setText(latitude + "," + altitude);
        this.location = location;
    }

    public void onMapsActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }
}
