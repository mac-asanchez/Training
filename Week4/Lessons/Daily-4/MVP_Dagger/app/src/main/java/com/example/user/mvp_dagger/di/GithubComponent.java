package com.example.user.mvp_dagger.di;

import com.example.user.mvp_dagger.view.github.GithubActivity;

import dagger.Component;

//Contract for dependencies and dependent
@Component(modules = GithubModule.class)
public interface GithubComponent {
    void inject(GithubActivity activity);
}
