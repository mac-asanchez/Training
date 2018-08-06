package com.example.user.mystore.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Timer;

import timber.log.Timber;

@Entity
public class Product {
    private static final String TAG = Product.class.getSimpleName() + "_TAG";
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int ProductId;

    String name;
    String brand;
    String description;
    String detail;
    String barCode;

    public Product() {
        Timber.tag(TAG).d("Product Constructor");
    }

    public Product(int productId, String name, String brand, String description, String detail, String barCode) {
        Timber.tag(TAG).d("Product Constructor with parameters");
        this.ProductId = productId;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.detail = detail;
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", barCode='" + barCode + '\'' +
                '}';
    }

    @NonNull
    public int getProductId() {
        return ProductId;
    }

    public void setProductId(@NonNull int productId) {
        ProductId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
