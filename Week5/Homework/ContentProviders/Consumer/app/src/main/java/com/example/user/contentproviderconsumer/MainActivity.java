package com.example.user.contentproviderconsumer;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.user.contentproviderconsumer.Adapter.RecyclerViewAdapter;
import com.example.user.contentproviderconsumer.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PermissionManager.Callback {
    public static final String CONTENT_URI = "com.mobileapps.training.AnimalsProvider";
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private PermissionManager permissionManager;
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rvList);

        Log.d(TAG, "onCreate: Check permission");
        permissionManager = new PermissionManager(this);
        permissionManager.checkPermission();
    }

    @Override
    public void onPermissionResults(int requestCode, boolean isGranted) {
        switch (requestCode) {
            case PermissionManager.MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (isGranted) {
                    Log.d(TAG, "onPermissionResults: Granted");
                    List<Product> contentProviderList = getProducts();

                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, contentProviderList);
                    rvList.setAdapter(adapter);
                    rvList.setLayoutManager(new LinearLayoutManager(this));
                } else {
                    Log.d(TAG, "onPermissionResults: Denied");
                }
                break;
            }

        }
    }

    private List<Product> getProducts(){
        Log.d(TAG, "onCreate: Cursor loading");
        Cursor cursor = this.getContentResolver().query(Uri.parse("content://com.example.user.contentprovider/products"),null,null,null,null);
        List<Product> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                int image = cursor.getInt(cursor.getColumnIndex("image"));

                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setImage(image);

                list.add(product);
            }while (cursor.moveToNext());
        }
        Log.d(TAG, "List products: " + list.toString());
        return list;
    }

    public void getProducts(View view) {
    }
}
