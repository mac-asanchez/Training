package com.example.user.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.fragments.fragments.BlueFragment;
import com.example.user.fragments.fragments.RedFragment;

public class MainActivity extends AppCompatActivity implements
        BlueFragment.OnFragmentInteractionListener,
        RedFragment.onFragmentInteractor {

    public static final String TAG = MainActivity.class.getSimpleName().toString() + "_TAG";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");
    }

    public void onAddingFragments(View view) {
        fragmentManager = getSupportFragmentManager();

        switch (view.getId()) {
            //region Add Red Fragment
            case R.id.btnAddRedFragment:
                RedFragment redFragment = (RedFragment) fragmentManager.findFragmentByTag(RedFragment.STRING_TAG);
                if (redFragment == null) {
                    redFragment = new RedFragment();

                    fragmentManager.beginTransaction()
                            .add(R.id.fragHolder1, redFragment, RedFragment.STRING_TAG)
                            .addToBackStack(RedFragment.STRING_TAG)
                            .commit();
                }
                break;
            //endregion

            //region Add Blue Fragment
            case R.id.btnAddBlueFragment:
                BlueFragment blueFragment = (BlueFragment) fragmentManager.findFragmentById(R.id.fragHolder2);
                if (blueFragment == null) {
                    blueFragment = BlueFragment.newInstance("data1", "data2");

                    fragmentManager.beginTransaction()
                            .add(R.id.fragHolder2, blueFragment)
                            .addToBackStack(BlueFragment.STRING_TAG)
                            .commit();
                }

                break;
            //endregion
        }
    }

    @Override
    public void onFragmentInteraction(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void onRemoveFragments(View view) {
        fragmentManager = getSupportFragmentManager();

        //get red fragment
        RedFragment redFragment = (RedFragment) fragmentManager.findFragmentByTag(RedFragment.STRING_TAG);

        //get blue fragment
        BlueFragment blueFragment = (BlueFragment) fragmentManager.findFragmentById(R.id.fragHolder2);

        removeFragmentIfNotNull(redFragment);
        removeFragmentIfNotNull(blueFragment);
    }

    private void removeFragmentIfNotNull(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment).commit();
            fragmentManager.popBackStackImmediate();
        }
    }

    @Override
    public void onDataFromRed(String data) {
        BlueFragment blueFragment = (BlueFragment) fragmentManager.findFragmentById(R.id.fragHolder2);

        if (blueFragment == null) {
            blueFragment = BlueFragment.newInstance("Data", data);

            fragmentManager.beginTransaction()
                    .add(R.id.fragHolder2, blueFragment, BlueFragment.STRING_TAG)
                    .addToBackStack(null)
                    .commit();
        } else {
            blueFragment.updateData(data);
        }
    }
}
