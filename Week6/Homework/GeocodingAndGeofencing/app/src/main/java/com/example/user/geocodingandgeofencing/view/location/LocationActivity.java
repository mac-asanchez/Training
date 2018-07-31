package com.example.user.geocodingandgeofencing.view.location;

import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.geocodingandgeofencing.R;

import java.io.IOException;

public class LocationActivity extends AppCompatActivity implements
        LocationContract.View, MapFragment.OnFragmentInteractionListener {
    private static final String TAG = LocationActivity.class.getSimpleName() + "_TAG";

    //region Controls
    LocationPresenter presenter;
    private TextView tvLocation;
    private EditText etNewLocation;
    private FragmentManager fragmentManager;
    //endregion

    //region Activity Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        presenter = new LocationPresenter(this);

        tvLocation = findViewById(R.id.tvLocation);
        etNewLocation = findViewById(R.id.etNewLocation);

        showMap(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        presenter.attachView(this);

        presenter.checkPermission();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        presenter.detachView();
    }
    //endregion

    //region Results from presenter
    @Override
    public void onPermissionResult(boolean isGranted) {
        Log.d(TAG, "onPermissionResult: " + isGranted);

        if (isGranted)
            presenter.getCurrentLocation();
    }

    @Override
    public void onLocationResult(Location location, Address address) {
        Log.d(TAG, "onLocationResult: " + address.toString());
        String strAddress = address.getAddressLine(0);
        tvLocation.setText(strAddress);
        showMap(location);
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: ");
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region Click Events
    public void onChangeLocation(View view) {
        Log.d(TAG, "onChangeLocation: Click");
        presenter.changeCurrentLocation(etNewLocation.getText().toString());
    }
    //endregion

    //region Map
    private void showMap(Location location) {
        Log.d(TAG, "showMap: ");
        fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentByTag(MapFragment.STRING_TAG);
        if (mapFragment == null) {
            mapFragment = mapFragment.newInstance();

            fragmentManager.beginTransaction()
                    .add(R.id.flMap, mapFragment, MapFragment.STRING_TAG)
                    .addToBackStack(MapFragment.STRING_TAG)
                    .commit();
        } else if (location != null) {
            mapFragment.updateLocation(location);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction: ");
    }
    //endregion
}
