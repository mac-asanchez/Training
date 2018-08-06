package com.example.user.mystore.model.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.user.mystore.model.entity.Product;

@Database(entities = Product.class, version = 1)
public abstract class StoreDataBase extends RoomDatabase {
    public abstract StoreDAO storeDAO();
}
