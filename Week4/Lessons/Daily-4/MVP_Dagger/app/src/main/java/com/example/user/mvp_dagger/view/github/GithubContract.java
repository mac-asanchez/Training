package com.example.user.mvp_dagger.view.github;

import com.example.user.mvp_dagger.model.github.Repo;
import com.example.user.mvp_dagger.view.base.BasePresenter;
import com.example.user.mvp_dagger.view.base.BaseView;

import java.util.List;

//interface used for communication
public interface GithubContract {

    //    for communication from Presenter to View
    interface View extends BaseView {

        void onValidationResults(String validName);

        void onRepoResult(List<Repo> repoList);

    }


    //    for communication from View to presenter
    interface Presenter extends BasePresenter<View> {

        void validateName(String name);

        void getRepos(String username);
    }

}