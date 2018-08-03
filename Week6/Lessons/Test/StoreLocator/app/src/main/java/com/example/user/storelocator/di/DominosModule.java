package com.example.user.storelocator.di;

import com.example.user.storelocator.model.data.remote.RemoteDataSource;
import com.example.user.storelocator.view.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DominosModule {
    @Provides
    RemoteDataSource providesRemoteDataSource() {
        return new RemoteDataSource();
    }

    @Provides
    MainPresenter providesMainPresenter(RemoteDataSource remoteDataSource) {
        return new MainPresenter(remoteDataSource);
    }
}
