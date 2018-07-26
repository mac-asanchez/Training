package com.example.user.recepiesapp.model.data.remote;

import android.util.Log;

import com.example.user.recepiesapp.model.edamam.Hit;
import com.example.user.recepiesapp.model.edamam.RecipesSearchResult;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private static final String TAG = RemoteDataSource.class.getSimpleName() + "_TAG";
    public static final String BASE_URL = "https://api.edamam.com/";
    public static final String API_ID = "b5144460";
    public static final String API_KEY = "ca6fdf110f00eacf3cb897aa351018bc";

    private Retrofit createInstance() {
        Log.d(TAG, "createInstance: ");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    private Observable<RecipesSearchResult> getRecipesFromNetwork(String query) {
        Log.d(TAG, "getRecipesFromNetwork: ");
        return createInstance().create(RemoteService.class).getRecepiesResult(API_ID, API_KEY, query);
    }

    public void getRecipes(String query, final Callback callback) {
        Log.d(TAG, "getRecipes: ");
        getRecipesFromNetwork(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecipesSearchResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(RecipesSearchResult recipesSearchResult) {
                        Log.d(TAG, "onNext: ");
                        callback.onRemoteResponse(recipesSearchResult.getHits());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        callback.onRemoteFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public interface Callback {
        void onRemoteResponse(List<Hit> recipes);

        void onRemoteFailure(String error);
    }
}
