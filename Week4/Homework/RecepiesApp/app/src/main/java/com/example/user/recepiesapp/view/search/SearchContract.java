package com.example.user.recepiesapp.view.search;

import com.example.user.recepiesapp.model.edamam.Hit;
import com.example.user.recepiesapp.view.base.BasePresenter;
import com.example.user.recepiesapp.view.base.BaseView;

import java.util.List;

//interface used for communication
public interface SearchContract {
    //    for communication from Presenter to View
    interface View extends BaseView {
        void onRecipesResult(List<Hit> recipeList);
    }

    //    for communication from View to presenter
    interface Presenter extends BasePresenter<View> {
        void getRecipes(String query);
    }
}
