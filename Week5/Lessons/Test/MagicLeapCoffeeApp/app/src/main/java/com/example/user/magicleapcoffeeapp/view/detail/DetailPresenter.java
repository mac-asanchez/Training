package com.example.user.magicleapcoffeeapp.view.detail;

import android.util.Log;

import com.example.user.magicleapcoffeeapp.model.data.remote.RemoteDataSource;
import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;
import com.example.user.magicleapcoffeeapp.view.search.SearchContract;

import java.util.List;

import javax.inject.Inject;

public class DetailPresenter implements DetailContract.Presenter {
    private static final String TAG = DetailPresenter.class.getSimpleName() + "_TAG";
    DetailContract.View view;
    RemoteDataSource remoteDataSource;

    @Inject
    public DetailPresenter(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void attachView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getCoffeeById(String coffeeId) {
        Log.d(TAG, "getCoffeeById: ");
        remoteDataSource.getCoffeeById(coffeeId, new RemoteDataSource.DetailCallback() {
            @Override
            public void onRemoteResponseById(CoffeeResult coffee) {
                view.onCoffeeResult(coffee);
            }

            @Override
            public void onRemoteFailure(String error) {
                view.showError(error);
            }
        });
    }
}