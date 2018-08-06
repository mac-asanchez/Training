package com.example.user.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.mystore.model.data.RoomHelper;
import com.example.user.mystore.model.entity.Product;
import com.example.user.mystore.utils.Analytics;
import com.flurry.android.FlurryAgent;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RoomHelper.Callback {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    //region Controls and Helper
    private RoomHelper roomHelper;
    private EditText etBarcode;
    private EditText etName;
    private EditText etBrand;
    private EditText etDescription;
    private EditText etDetail;
    //endregion

    //region Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Room
        roomHelper = new RoomHelper(this);
        //endregion

        getControls();
    }

    private void getControls() {
        Timber.tag(TAG).d("get Controls");
        etBarcode = findViewById(R.id.etBarCode);
        etName = findViewById(R.id.etName);
        etBrand = findViewById(R.id.etBrand);
        etDescription = findViewById(R.id.etDescription);
        etDetail = findViewById(R.id.etDetail);

    }

    private void cleanForm() {
        Timber.tag(TAG).d("Clean Form");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etBarcode.setText("");
                etName.setText("");
                etBrand.setText("");
                etDescription.setText("");
                etDetail.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);*/
    }

    //endregion

    //region Scan BarCode and Show Product
    public void onScanBarCode(View view) {
        Timber.tag(TAG).d("onScanBarCode");
        FlurryAgent.logEvent(Analytics.BARCODE_SCAN);
        try {
            new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
        } catch (Exception ex) {
            Timber.tag(TAG).e(ex);
            ex.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String barCode = result.getContents();
                //Toast.makeText(this, "Scanned: " + barCode, Toast.LENGTH_LONG).show();
                Log.d(TAG, "onActivityResult: barCode: " + barCode);
                FlurryAgent.logEvent(Analytics.BARCODE_SCANED);
                roomHelper.getProduct(barCode);
                etBarcode.setText(barCode);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onGetProduct(final Product product) {
        if (product != null) {
            Log.d(TAG, "onGetProduct: product: " + product.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    etName.setText(product.getName());
                    etBrand.setText(product.getBrand());
                    etDescription.setText(product.getDescription());
                    etDetail.setText(product.getDetail());
                }
            });
        } else {
            Log.d(TAG, "onGetProduct: null");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    etName.requestFocus();
                }
            });
        }
    }
    //endregion

    //region Save Product
    public void onProductSave(View view) {
        //Log.d(TAG, "onProductSave: ");
        Timber.tag(TAG).d("onProductSave: ");
        FlurryAgent.logEvent(Analytics.PRODUCT_ADD);
        Product product = new Product();
        product.setBarCode(etBarcode.getText().toString());
        product.setName(etName.getText().toString());
        product.setBrand(etBrand.getText().toString());
        product.setDescription(etDescription.getText().toString());
        product.setDetail(etDetail.getText().toString());

        roomHelper.saveProduct(product);
    }

    public void onCancel(View view) {
        Timber.tag(TAG).d("onCancel");
        cleanForm();
    }

    @Override
    public void onSaveProduct(Product product) {
        if (product != null) {
            //Log.d(TAG, "onSaveProduct: " + product.toString());
            Timber.tag(TAG).d("onSaveProduct: %s", product.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Timber.tag(TAG).d("Product Saved");
                    FlurryAgent.logEvent(Analytics.PRODUCT_ADDED);
                    Toast.makeText(MainActivity.this, "Product Saved", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //Log.d(TAG, "onSaveProduct: null");
            Timber.tag(TAG).d("Product not saved");
            FlurryAgent.logEvent(Analytics.PRODUCT_ADD_ERROR);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

        cleanForm();
    }
    //endregion

    //region Database interaction
    @Override
    public void onProductsList(List<Product> productList) {
        //Log.d(TAG, "onProductsList: " + productList.size());
        Timber.tag(TAG).d("onProductList");
    }
    //endregion
}
