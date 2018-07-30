package com.example.user.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductDBHelper extends SQLiteOpenHelper  {
    private static final String TAG = ProductDBHelper.class.getSimpleName() + "_TAG";
    static final String DB_NAME = "Inventory.db";
    static final int VERSION = 1;
    Context context;

    public ProductDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        Log.d(TAG, "ProductDBHelper: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: " + ProductContract.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(ProductContract.SQL_CREATE_ENTRIES);

        for(String insert: ProductContract.SQL_INSERT_PRODUCTS_INITIAL_LOAD()){
            Log.d(TAG, "onCreate: " + insert);
            sqLiteDatabase.execSQL(insert);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL(ProductContract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
