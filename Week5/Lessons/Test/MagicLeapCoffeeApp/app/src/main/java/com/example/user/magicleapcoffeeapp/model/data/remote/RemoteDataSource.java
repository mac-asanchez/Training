package com.example.user.magicleapcoffeeapp.model.data.remote;

import android.util.Log;

import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;

import java.util.ArrayList;
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
    public static final String BASE_URL = "https://demo6983184.mockable.io/";
    private static final String TAG = RemoteDataSource.class.getSimpleName() + "_TAG";

    private Retrofit createInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    private Observable<List<CoffeeResult>> getCoffeesFromNetwork() {
        Log.d(TAG, "getRecipesFromNetwork: ");
        return createInstance().create(RemoteService.class).getCoffees();
    }

    private Observable<CoffeeResult> getCoffeeByIdFromNetWork(String coffeeId) {
        Log.d(TAG, "getCoffeeByIdFromNetWork: ");
        return createInstance().create(RemoteService.class).getCoffeeById(coffeeId);
    }

    public void getCoffees(final Callback callback) {
        Log.d(TAG, "getCoffees: ");
        getCoffeesFromNetwork()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CoffeeResult>>() {
                    List<CoffeeResult> coffeeResultList;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<CoffeeResult> coffeeResults) {
                        Log.d(TAG, "onNext: " + coffeeResults.size());

                        if (coffeeResults.size() > 0)
                            coffeeResultList = coffeeResults;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        callback.onRemoteFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                        callback.onRemoteResponse(coffeeResultList);
                    }
                });
    }

    public void getCoffeeById(String coffeeId, final DetailCallback callback) {
        Log.d(TAG, "getCoffeeById: ");
        getCoffeeByIdFromNetWork(coffeeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CoffeeResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(CoffeeResult coffeeResult) {
                        Log.d(TAG, "onNext: ");
                        callback.onRemoteResponseById(coffeeResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        callback.onRemoteFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public interface Callback {
        void onRemoteResponse(List<CoffeeResult> coffees);
        void onRemoteFailure(String error);
    }

    public interface DetailCallback {
        void onRemoteResponseById(CoffeeResult coffee);
        void onRemoteFailure(String error);
    }
}
