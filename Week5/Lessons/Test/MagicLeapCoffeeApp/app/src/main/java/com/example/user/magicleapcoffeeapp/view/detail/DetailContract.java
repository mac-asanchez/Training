package com.example.user.magicleapcoffeeapp.view.detail;

import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;
import com.example.user.magicleapcoffeeapp.view.base.BasePresenter;
import com.example.user.magicleapcoffeeapp.view.base.BaseView;

public interface DetailContract {
    //    for communication from Presenter to View
    interface View extends BaseView {
        void onCoffeeResult(CoffeeResult coffeeResult);
    }

    //    for communication from View to presenter
    interface Presenter extends BasePresenter<View> {
        void getCoffeeById(String coffeeId);
    }
}
