package com.example.user.recepiesapp.view.search;

import android.util.Log;

import com.example.user.recepiesapp.model.data.remote.RemoteDataSource;
import com.example.user.recepiesapp.model.edamam.Hit;

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
    public void getRecipes(String query) {
        Log.d(TAG, "getRecipes: ");
        remoteDataSource.getRecipes(query, new RemoteDataSource.Callback() {
            @Override
            public void onRemoteResponse(List<Hit> recipes) {
                Log.d(TAG, "onRemoteResponse: ");
                view.onRecipesResult(recipes);
            }

            @Override
            public void onRemoteFailure(String error) {
                Log.d(TAG, "onRemoteFailure: " + error);
                view.showError(error);
            }
        });
    }

    @Override
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
