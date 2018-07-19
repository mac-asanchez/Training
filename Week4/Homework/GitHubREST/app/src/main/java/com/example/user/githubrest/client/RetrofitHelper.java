package com.example.user.githubrest.client;

import android.util.Log;

import com.example.user.githubrest.model.GitHubProfile;
import com.example.user.githubrest.model.GitHubRepo;
import com.example.user.githubrest.utils.HandlerUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitHelper {
    public static final String TAG = RetrofitHelper.class.getSimpleName() + "_TAG";
    private static final String BASE_URL = "https://api.github.com/";
    private static final String USER = "mac-asanchez";

    private Retrofit createClient(boolean AuthorizeWithToken) {

        Retrofit retrofit;

        if (AuthorizeWithToken) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addNetworkInterceptor(new AuthInterceptor());
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    private Call<GitHubProfile> getUserProfile() {
        Retrofit retrofit = createClient(true);
        Service service = retrofit.create(Service.class);
        return service.getUserProfile();
    }

    private Call<List<GitHubRepo>> getUserRepos() {
        Log.d(TAG, "getUserRepos: ");
        Retrofit retrofit = createClient(false);
        Service service = retrofit.create(Service.class);
        return service.getUserRepos();
    }

    public void getUserProfileInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GitHubProfile apiResponse = getUserProfile().execute().body();
                    HandlerUtils.getDefault().sendMessage(apiResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getUserReposList() {
        Log.d(TAG, "getUserReposList: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: ");
                    List<GitHubRepo> apiResponse = getUserRepos().execute().body();
                    HandlerUtils.getDefault().sendListMessage(apiResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //    interface to use Htpp verbs
    interface Service {
        @GET("users/" + USER)
        Call<GitHubProfile> getUserProfile();

        @GET("users/" + USER + "/repos")
        Call<List<GitHubRepo>> getUserRepos();
    }
}
