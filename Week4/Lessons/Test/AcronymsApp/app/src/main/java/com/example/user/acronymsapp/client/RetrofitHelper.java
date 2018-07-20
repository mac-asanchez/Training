package com.example.user.acronymsapp.client;

import android.util.Log;

import com.example.user.acronymsapp.model.APIResult;
import com.example.user.acronymsapp.utils.HandlerUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitHelper {
    public static final String TAG = RetrofitHelper.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "http://www.nactem.ac.uk/";

    private Retrofit createClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Call<List<APIResult>> getDefinitions(String acronym) {
        Log.d(TAG, "getDefinitions: ");
        Retrofit retrofit = createClient();
        Service service = retrofit.create(Service.class);
        return service.getDefinitions(acronym);
    }

    public void getDefinitionsList(final String acronym) {
        Log.d(TAG, "getDefinitionsList: " + acronym);
        getDefinitions(acronym).enqueue(new Callback<List<APIResult>>() {
            @Override
            public void onResponse(Call<List<APIResult>> call, Response<List<APIResult>> response) {
                Log.d(TAG, "onResponse: ");
                List<APIResult> apiResponse = response.body();
                if (apiResponse != null && apiResponse.size() > 0) {
                    HandlerUtils.getDefault().sendMessage(apiResponse.get(0).getLfs());
                }
            }

            @Override
            public void onFailure(Call<List<APIResult>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    //    interface to use Htpp verbs
    interface Service {
        @GET("software/acromine/dictionary.py")
        Call<List<APIResult>> getDefinitions(@Query("sf") String acronym);
    }
}
