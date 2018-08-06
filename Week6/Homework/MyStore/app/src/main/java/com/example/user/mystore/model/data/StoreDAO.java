package com.example.user.mystore.model.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.user.mystore.model.entity.Product;

import java.util.List;

@Dao
public interface StoreDAO {
    @Insert
    void saveProduct(Product product);

    @Query("SELECT * FROM Product")
    List<Product> getProducts();

    @Query("SELECT * FROM Product AS a WHERE a.barCode = :barCode")
    List<Product> getProductByBarCode(String barCode);
}
