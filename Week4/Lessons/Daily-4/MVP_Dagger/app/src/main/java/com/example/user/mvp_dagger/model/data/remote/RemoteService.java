package com.example.user.mvp_dagger.model.data.remote;

import com.example.user.mvp_dagger.model.github.Repo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteService {
    @GET("users/{username}/repos")
    Observable<List<Repo>> getRepos(@Path("username") String username);
}
