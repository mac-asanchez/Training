package com.example.user.storelocator.model.data.remote;

import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {
    @GET("power/store-locator")
    Call<ResponseBody> getStores(@Query("s") int zipCode);
}
