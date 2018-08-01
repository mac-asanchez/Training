package com.example.user.encryption.view.main;

import com.example.user.encryption.model.Person;
import com.example.user.encryption.view.base.BasePresenter;
import com.example.user.encryption.view.base.BaseView;

public interface MainContract {
    interface View extends BaseView {

        void onEncryption(String encryptedData);

        void onDecryption(Person person);
    }

    interface Presenter extends BasePresenter<View> {
        void encryptData(Person person);

        void decryptData(String encyptedData);
    }
}
