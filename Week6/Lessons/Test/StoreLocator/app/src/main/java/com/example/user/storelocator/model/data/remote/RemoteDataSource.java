package com.example.user.storelocator.model.data.remote;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.user.storelocator.model.dominos.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private static final String TAG = RemoteDataSource.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://order.dominos.com/";

    private Retrofit createInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        return retrofit;
    }

    private Call<ResponseBody> getStoresFromNetwork(int zipCode) {
        Log.d(TAG, "getStoresFromNetwork: ");
        return createInstance().create(RemoteService.class).getStores(zipCode);
    }

    public void getStores(final int zipCode, final Callback callback) {
        Log.d(TAG, "getStores: ");
        getStoresFromNetwork(zipCode).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String strResponse = response.body().string();
                    List<Store> storeList = new ArrayList<>();

                    // get Response String
                    JSONObject jsonObject = new JSONObject(strResponse);

                    JSONArray jsonArray = jsonObject.getJSONArray("Stores");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            int storeId = ((JSONObject) jsonArray.get(i)).getInt("StoreID");
                            String address = ((JSONObject) jsonArray.get(i)).get("AddressDescription").toString();
                            String hourDescription = ((JSONObject) jsonArray.get(i)).get("HoursDescription").toString();
                            storeList.add(new Store(storeId, address, hourDescription));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    callback.onRemoteResponse(storeList);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                callback.onRemoteFailure(t.getMessage());
            }
        });
    }

    public interface Callback {
        void onRemoteResponse(List<Store> stores);

        void onRemoteFailure(String error);
    }
}
