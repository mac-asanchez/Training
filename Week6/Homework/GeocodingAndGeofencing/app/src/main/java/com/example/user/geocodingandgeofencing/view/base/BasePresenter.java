package com.example.user.geocodingandgeofencing.view.base;

//common method for the Presenter
public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
