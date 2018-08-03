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
        ZipCodeFragment.OnFragmentInteractionListener {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    @Inject
    MainPresenter presenter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        addZipCodeFragment();
    }

    private void addZipCodeFragment() {
        fragmentManager = getSupportFragmentManager();
        ZipCodeFragment celebrities = (ZipCodeFragment) fragmentManager.findFragmentByTag(ZipCodeFragment.STRING_TAG);
        if (celebrities == null) {
            celebrities = ZipCodeFragment.newInstance("", "");

            fragmentManager.beginTransaction()
                    .add(R.id.flZipCode, celebrities, ZipCodeFragment.STRING_TAG)
                    //.addToBackStack(ZipCodeFragment.STRING_TAG)
                    .commit();
        }
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

    @Override
    public void onStoresResult(List<Store> storeList) {
        Log.d(TAG, "onStoresResult: " + storeList.size());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    public void onSearchStore(View view){
        EditText etZipCode = findViewById(R.id.etZipCode);
        presenter.getStores(Integer.parseInt(etZipCode.getText().toString()));
    }

    @Override
    public void onFragmentInteraction(View view) {

    }
}
