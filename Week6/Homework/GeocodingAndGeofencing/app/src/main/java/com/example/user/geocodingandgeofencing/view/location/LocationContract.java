package com.example.user.geocodingandgeofencing.view.location;

import android.location.Address;
import android.location.Location;

import com.example.user.geocodingandgeofencing.view.base.BasePresenter;
import com.example.user.geocodingandgeofencing.view.base.BaseView;

import java.util.List;

//interface used for communication
public interface LocationContract {
    //    for communication from Presenter to View
    interface View extends BaseView {
        void onPermissionResult(boolean isGranted);
        void onLocationResult(Location location, Address address);
    }

    //    for communication from View to presenter
    interface Presenter extends BasePresenter<View> {
        void checkPermission();
        void getCurrentLocation();
        void changeCurrentLocation(String strAddress);
    }
}
