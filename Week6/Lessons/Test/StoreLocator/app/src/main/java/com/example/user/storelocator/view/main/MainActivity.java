package com.example.user.storelocator.view.main;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.storelocator.R;
import com.example.user.storelocator.di.DaggerDominosComponent;
import com.example.user.storelocator.model.dominos.Store;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements
        MainContract.View,
        ZipCodeFragment.OnFragmentInteractionListener,
        StoreListFragment.OnFragmentInteractionListener {
    //region variables
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    @Inject
    MainPresenter presenter;
    private FragmentManager fragmentManager;
    //endregion

    //region Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        addZipCodeFragment();
        addStoreListFragment(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        DaggerDominosComponent.create().inject(this);
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        presenter.detachView();
    }
    //endregion

    //region on Events
    public void onSearchStore(View view) {
        EditText etZipCode = findViewById(R.id.etZipCode);
        presenter.getStores(Integer.parseInt(etZipCode.getText().toString()));
    }

    @Override
    public void onFragmentInteraction(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void addZipCodeFragment() {
        fragmentManager = getSupportFragmentManager();
        ZipCodeFragment zipCodeFragment = (ZipCodeFragment) fragmentManager.findFragmentByTag(ZipCodeFragment.STRING_TAG);
        if (zipCodeFragment == null) {
            zipCodeFragment = ZipCodeFragment.newInstance("", "");

            fragmentManager.beginTransaction()
                    .add(R.id.flZipCode, zipCodeFragment, ZipCodeFragment.STRING_TAG)
                    //.addToBackStack(ZipCodeFragment.STRING_TAG)
                    .commit();
        }
    }

    private void addStoreListFragment(List<Store> storeList) {
        fragmentManager = getSupportFragmentManager();
        StoreListFragment storeListFragment = (StoreListFragment) fragmentManager.findFragmentByTag(StoreListFragment.STRING_TAG);
        if (storeListFragment == null) {
            storeListFragment = StoreListFragment.newInstance("", "");

            fragmentManager.beginTransaction()
                    .add(R.id.flDetails, storeListFragment, StoreListFragment.STRING_TAG)
                    //.addToBackStack(ZipCodeFragment.STRING_TAG)
                    .commit();
        } else {
            storeListFragment.updateData(storeList);
        }
    }
    //endregion

    //region on Results
    @Override
    public void onStoresResult(List<Store> storeList) {
        Log.d(TAG, "onStoresResult: " + storeList.size());
        addStoreListFragment(storeList);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
    //endregion
}
