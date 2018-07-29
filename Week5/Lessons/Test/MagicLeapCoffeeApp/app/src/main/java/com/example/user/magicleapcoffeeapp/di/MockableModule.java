package com.example.user.magicleapcoffeeapp.di;

import com.example.user.magicleapcoffeeapp.model.data.remote.RemoteDataSource;
import com.example.user.magicleapcoffeeapp.view.detail.DetailPresenter;
import com.example.user.magicleapcoffeeapp.view.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MockableModule {

    @Provides
    SearchPresenter providesSearchPresenter(RemoteDataSource remoteDataSource) {
        return new SearchPresenter(remoteDataSource);
    }

    @Provides
    RemoteDataSource provideRemoteDataSource() {
        return new RemoteDataSource();
    }

    @Provides
    DetailPresenter providesDetailPresenter(RemoteDataSource remoteDataSource) {
        return new DetailPresenter(remoteDataSource);
    }
}
