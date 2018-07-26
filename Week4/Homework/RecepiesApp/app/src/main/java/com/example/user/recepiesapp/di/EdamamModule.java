package com.example.user.recepiesapp.di;

import com.example.user.recepiesapp.model.data.remote.RemoteDataSource;
import com.example.user.recepiesapp.view.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EdamamModule {

    @Provides
    SearchPresenter providesSearchPresenter(RemoteDataSource remoteDataSource) {
        return new SearchPresenter(remoteDataSource);
    }

    @Provides
    RemoteDataSource provideRemoteDataSource() {
        return new RemoteDataSource();
    }
}
