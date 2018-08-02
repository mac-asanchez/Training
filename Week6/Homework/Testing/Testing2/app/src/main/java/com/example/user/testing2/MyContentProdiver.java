package com.example.user.testing2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyContentProdiver extends ContentProvider {
    private static final String TAG = MyContentProdiver.class.getSimpleName() + "_TAG";
    private static final int PRODUCTS = 100;
    private static final int PRODUCT_ID = 101;

    /*UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for
     * the root URI. It's common to use NO_MATCH as the input for this case. */
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
//        The calls to addURI() go here, for all the other content URI patterns that the provider
//        should recognize. All paths added to the UriMatcher have a corresponding code to return
//        when a match is found.
//        The content URI of the form "content://com.example.user.contentprovider/products" will map to the
//        integer code {@link #product}. This URI is used to provide access to MULTIPLE rows
//        of the product table
        uriMatcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_PRODUCTS, PRODUCTS);

//        The content URI of the form "content://com.example.user.contentprovider/products/#" will map to the
//        integer code {@link #PRODUCT_ID}. This URI is used to provide access to the ONE single row
//        of the products table.
//        In this case, the "#" wildcard is used where "#" can be substituted for an integer.
//        For example, "content://com.example.user.contentprovider/products/3" matches, but
//        "content://com.example.user.contentprovider/products" (without a number at the end) doesn't match.
        uriMatcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
    }

    private ProductDBHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ");
        mDbHelper = new ProductDBHelper(getContext());
        db = mDbHelper.getWritableDatabase();
        return mDbHelper != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selecttionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query: Uri: " + uri.toString());
        db = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);

        switch (match) {
            case PRODUCTS:
//                For the PRODUCTS code, query the products table directly with the given
//                projection, selection, selection arguments, and sort order. The cursor
//                could obtain multiple rows of the pets table.
                cursor = db.query(ProductContract.ProductEntry.TABLE_NAME, projection, selection, selecttionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
//                For the PRODUCT_ID code, extract out the ID from the URI.
//                For an example URI such as "content://com.example.user.contentprovider/products/25",
//                the selection will be "_id=?" and the selection argument will be a
//                String array containing the actual ID of 3 in this case.
//
//                For every "?" in the selection, we need to have an element in the selection
//                arguments that will fill in the "?". Since we have 1 question mark in the
//                selection, we have 1 String in the selection arguments' String array.
                selection = ProductContract.ProductEntry._ID + "=?";
                selecttionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

//                This will perform a query on the products table where the _id equals 25 to return a
//                Cursor containing that row of the table.
                cursor = db.query(ProductContract.ProductEntry.TABLE_NAME, projection, selection, selecttionArgs,
                        null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType: Uri: " + uri.toString());
        final int match = uriMatcher.match(uri);

        switch (match) {
            case PRODUCTS:
                return ProductContract.ProductEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return ProductContract.ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
