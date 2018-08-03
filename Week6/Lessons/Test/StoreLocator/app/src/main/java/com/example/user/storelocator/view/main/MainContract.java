package com.example.user.storelocator.view.main;

import com.example.user.storelocator.model.dominos.Store;
import com.example.user.storelocator.view.base.BasePresenter;
import com.example.user.storelocator.view.base.BaseView;

import java.util.List;

public interface MainContract {
    interface View extends BaseView {
        void onStoresResult(List<Store> storeList);
    }

    interface Presenter extends BasePresenter<View> {
        void getStores(int zipCode);
    }
}
