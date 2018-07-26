package com.example.user.recepiesapp.di;

import com.example.user.recepiesapp.view.search.SearchActivity;

import dagger.Component;

@Component(modules = EdamamModule.class)
public interface EdamamComponent {
    void inject(SearchActivity activity);
}
