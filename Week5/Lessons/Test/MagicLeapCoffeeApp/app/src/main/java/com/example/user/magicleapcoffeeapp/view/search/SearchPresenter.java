package com.example.user.magicleapcoffeeapp.view.search;

import android.util.Log;

import com.example.user.magicleapcoffeeapp.model.data.remote.RemoteDataSource;
import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {
    private static final String TAG = SearchPresenter.class.getSimpleName() + "_TAG";
    SearchContract.View view;
    RemoteDataSource remoteDataSource;

    @Inject
    public SearchPresenter(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getCoffees() {
        Log.d(TAG, "getCoffees: ");

        remoteDataSource.getCoffees(new RemoteDataSource.Callback() {
            @Override
            public void onRemoteResponse(List<CoffeeResult> coffees) {
                Log.d(TAG, "onRemoteResponse: ");
                view.onCoffeesResult(coffees);
            }

            @Override
            public void onRemoteFailure(String error) {
                Log.d(TAG, "onRemoteFailure: ");
                view.showError(error);
            }
        });
    }
}
