package com.example.user.magicleapcoffeeapp.view.search;

import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;
import com.example.user.magicleapcoffeeapp.view.base.BasePresenter;
import com.example.user.magicleapcoffeeapp.view.base.BaseView;

import java.util.List;

//interface used for communication
public interface SearchContract {
    //    for communication from Presenter to View
    interface View extends BaseView {
        void onCoffeesResult(List<CoffeeResult> coffeeResultList);
    }

    //    for communication from View to presenter
    interface Presenter extends BasePresenter<View> {
        void getCoffees();
    }
}
