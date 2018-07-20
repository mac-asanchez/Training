package com.example.user.mvp_dagger.view.github;

public class GithubPresenter implements GithubContract.Presenter {
    GithubContract.View view;

    @Override
    public void validateName(String name) {
        if (name.equals("something"))
            view.showError("This is not a valid name.");
        else
            view.onValidationResults("Mr/Mrs " + name);
    }

    @Override
    public void attachView(GithubContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
