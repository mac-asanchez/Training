package com.example.user.mvp_dagger.view.base;

//common method for the Presenter
public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
