package com.example.user.magicleapcoffeeapp.model.data.remote;

import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteService {
    @GET("coffees")
    Observable<List<CoffeeResult>> getCoffees();

    @GET("coffees/{coffeeId}")
    Observable<CoffeeResult> getCoffeeById(@Path("coffeeId") String coffeeId);
}
