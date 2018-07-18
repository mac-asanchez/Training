package com.example.user.makingrestcalls.client;

import android.app.Activity;
import android.util.Log;

import com.example.user.makingrestcalls.model.APIResponse;
import com.example.user.makingrestcalls.utils.HandlerUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpHelper {
    public static final String BASE_URL = "https://randomuser.me/api?results=10";
    public static final String BASE_URL2 = "https://randomuser.me/";
    public static final String PATH = "api";
    public static final String QUERY_RESULTS = "results";

    OkHttpClient client;
    private final Request request;
    private String TAG = OkHttpClient.class.getSimpleName() + "_TAG";

    public OkhttpHelper() {
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(getUrl())
                .build();
    }

    public HttpUrl getUrl(){
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("randomuser.me")
                .addPathSegment(PATH)
                .addQueryParameter(QUERY_RESULTS, "10")
                .build();


        return httpUrl;
    }

    public void executeSyncCall() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String responseStr = null;
                try {
                    responseStr = client.newCall(request).execute().body().string();
                    Gson gson = new Gson();
                    APIResponse response = gson.fromJson(responseStr, APIResponse.class);
                    Log.d(TAG, "executeSyncCall: Results size: " + response.getResults().size());
                    String size = String.valueOf(response.getResults().size());
                    HandlerUtils.getDefault().sendMessage(size);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void executeAsyncCall(){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson =  new Gson();
                APIResponse apiResponse = gson.fromJson(response.body().string(), APIResponse.class);
                Log.d(TAG, "onResponse: Thread: " + Thread.currentThread().getName());
                Log.d(TAG, "onResponse: Size: " + apiResponse.getResults().size());
            }
        });
    }

    public void executeAsyncCall2(Activity activity, Callback callback){

    }
}
