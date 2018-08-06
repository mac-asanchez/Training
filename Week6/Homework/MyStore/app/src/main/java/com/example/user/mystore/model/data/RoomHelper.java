package com.example.user.mystore.model.data;

import android.content.Context;
import android.util.Log;

import com.example.user.mystore.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static android.arch.persistence.room.Room.databaseBuilder;

public class RoomHelper {
    private static final String TAG = RoomHelper.class.getSimpleName() + "_TAG";
    Context context;
    StoreDataBase database;
    StoreDAO storeDAO;
    Callback callback;

    public RoomHelper(Context context) {
        //Log.d(TAG, "RoomHelper: Constructor");
        Timber.tag(TAG).d("RoomHelper: Constructor");
        this.context = context;
        this.database = databaseBuilder(context, StoreDataBase.class, "Store-database").build();
        this.callback = (Callback) context;
        this.storeDAO = database.storeDAO();
    }

    public void saveProduct(final Product product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                storeDAO.saveProduct(product);
                //Log.d(TAG, "run: saveProduct: " + product.toString());
                Timber.tag(TAG).d("run: saveProduct: %s", product.toString());
                callback.onSaveProduct(product);
            }
        }).start();
    }

    public void getProducts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Product> productList = storeDAO.getProducts();
                //Log.d(TAG, "run: getProducts: " + productList.toString());
                Timber.tag(TAG).d("run: getProducts: %s", productList.size());
                callback.onProductsList(productList);
            }
        }).start();
    }

    public void getProduct(final String barCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Product> productList = storeDAO.getProductByBarCode(barCode);
                //Log.d(TAG, "run: getProduct: " + productList.size());
                Timber.tag(TAG).d("run: getProduct: %s", productList.size());
                if (productList.size() > 0)
                    callback.onGetProduct(productList.get(0));
                else
                    callback.onGetProduct(null);
            }
        }).start();
    }

    public interface Callback {
        void onSaveProduct(Product product);

        void onProductsList(List<Product> productList);

        void onGetProduct(Product product);
    }
}