package com.example.user.magicleapcoffeeapp.di;

import com.example.user.magicleapcoffeeapp.view.detail.DetailActivity;
import com.example.user.magicleapcoffeeapp.view.search.SearchActivity;

import dagger.Component;

@Component(modules = MockableModule.class)
public interface MockableComponent {
    void inject(SearchActivity activity);
    void injectDetails(DetailActivity activity);
}
