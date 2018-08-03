package com.example.user.storelocator.view.base;

//common method for the Presenter
public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}

