package com.example.user.mvp_dagger.di;

//dependencies

import com.example.user.mvp_dagger.model.data.remote.RemoteDataSource;
import com.example.user.mvp_dagger.view.github.GithubPresenter;

import dagger.Module;
import dagger.Provides;

//add all the dependencies in this class
@Module
public class GithubModule {

    //    each method would return the dependency required
    @Provides
    GithubPresenter providesGithubPresenter(RemoteDataSource remoteDataSource) {
        return new GithubPresenter(remoteDataSource);
    }


    @Provides
    RemoteDataSource provideRemoteDataSource() {
        return new RemoteDataSource();
    }
}
