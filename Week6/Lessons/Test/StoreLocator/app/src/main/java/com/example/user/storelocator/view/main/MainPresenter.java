package com.example.user.storelocator.view.main;

import android.util.Log;

import com.example.user.storelocator.model.data.remote.RemoteDataSource;
import com.example.user.storelocator.model.dominos.Store;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    MainContract.View view;
    RemoteDataSource remoteDataSource;

    @Inject
    public MainPresenter(RemoteDataSource remoteDataSource) {
        Log.d(TAG, "MainPresenter: ");
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getStores(int zipCode) {
        Log.d(TAG, "getStores: ");
        remoteDataSource.getStores(zipCode, new RemoteDataSource.Callback() {
            @Override
            public void onRemoteResponse(List<Store> stores) {
                Log.d(TAG, "onRemoteResponse: ");
                view.onStoresResult(stores);
            }

            @Override
            public void onRemoteFailure(String error) {
                view.showError(error);
            }
        });
    }

    @Override
    public void attachView(MainContract.View view) {
        Log.d(TAG, "attachView: ");
        this.view = view;
    }

    @Override
    public void detachView() {
        Log.d(TAG, "detachView: ");
        this.view = null;
    }
}
