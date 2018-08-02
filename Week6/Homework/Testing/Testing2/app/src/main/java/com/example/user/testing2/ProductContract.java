package com.example.user.testing2;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductContract {
    public static final String PATH_PRODUCTS = "products";
    static final String CONTENT_AUTHORITY = "com.example.user.contentprovider";
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" +
                    ProductEntry._ID + " INTEGER PRIMARY KEY, " +
                    ProductEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    ProductEntry.COLUMN_QUANTITY + " INTEGER DEFAULT 0, " +
                    ProductEntry.COLUMN_PRICE + " INTEGER DEFAULT 0, " +
                    ProductEntry.COLUMN_IMAGE + " INTEGER NOT NULL)";
    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;
    private static final String TAG = ProductContract.class.getSimpleName() + "_TAG";
    static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //To prevent someone from coincidentally instantiating the
    //contract class make the constructor private
    private ProductContract() {
    }

    static List<String> SQL_INSERT_PRODUCTS_INITIAL_LOAD() {
        Log.d(TAG, "SQL_INSERT_PRODUCTS_INITIAL_LOAD: ");
        List<String> InitialLoad = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            String cont = String.valueOf(i);
            String quantity = String.valueOf(new Random().nextInt(100));
            String price = String.valueOf(new Random().nextFloat() * 100);
            String image = String.valueOf(new Random().nextInt(100));

            String row = "INSERT INTO " + ProductEntry.TABLE_NAME +
                    " (" + ProductEntry._ID + ", " + ProductEntry.COLUMN_NAME + ", " + ProductEntry.COLUMN_QUANTITY + ", " + ProductEntry.COLUMN_PRICE + ", " + ProductEntry.COLUMN_IMAGE + ")" +
                    " VALUES (" + cont + ", 'Product " + cont + "', " + quantity + ", " + price + ", " + image + ") ";

            InitialLoad.add(row);
        }

        return InitialLoad;
    }

    public static class ProductEntry implements BaseColumns {
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE = "image";
        public static final String TABLE_NAME = "products";
        //The MIME type of the {@link #CONTENT_URI} for a list of products
        static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        //The MIME type of the {@link #CONTENT_URI} for a single product.
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        private static final String TAG = ProductEntry.class.getSimpleName() + "_TAG";
        //The content URI to access the product data in the provider
        private static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);
    }
}
