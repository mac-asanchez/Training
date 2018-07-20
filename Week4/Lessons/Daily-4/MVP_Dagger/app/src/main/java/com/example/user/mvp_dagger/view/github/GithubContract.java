package com.example.user.mvp_dagger.view.github;

import com.example.user.mvp_dagger.view.base.BasePresenter;
import com.example.user.mvp_dagger.view.base.BaseView;

//interface used for communication
public interface GithubContract {
    //    for communication from the presenter to the view
    interface View extends BaseView {
        void onValidationResults(String validName);
    }

    //    for communication from View to presenter
    interface Presenter extends BasePresenter<View> {
        void validateName(String name);
    }
}
