package com.example.user.recepiesapp.model.data.remote;

import com.example.user.recepiesapp.model.edamam.RecipesSearchResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {

    @GET("search")
    Observable<RecipesSearchResult> getRecepiesResult(@Query("app_id") String app_id, @Query("app_key") String app_key, @Query("q") String q);
}
