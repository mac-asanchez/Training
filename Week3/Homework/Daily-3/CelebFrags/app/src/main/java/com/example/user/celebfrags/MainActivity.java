package com.example.user.celebfrags;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.celebfrags.adapter.DataCreator;
import com.example.user.celebfrags.model.Celebrity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        Celebrities.OnFragmentInteractionListener,
        Details.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCelebritiesList();
    }

    private void addCelebritiesList() {
        fragmentManager = getSupportFragmentManager();
        Celebrities celebrities = (Celebrities) fragmentManager.findFragmentByTag(Celebrities.STRING_TAG);
        if (celebrities == null) {
            celebrities = Celebrities.newInstance("", "");

            fragmentManager.beginTransaction()
                    .add(R.id.flCelebrities, celebrities, Celebrities.STRING_TAG)
                    .addToBackStack(Celebrities.STRING_TAG)
                    .commit();
        }
    }

    public void onCelebritySelected(View view) {
        TextView tvCelebrity = (TextView) view;
        int CelebrityId = Integer.parseInt(tvCelebrity.getTag().toString());

        List<Celebrity> celebrityList = DataCreator.getCelebrities();
        for (Celebrity celebrity : celebrityList) {
            if (celebrity.getCelebrityId() == CelebrityId) {
                showCelebrityDetails(celebrity);
                break;
            }
        }
    }

    private void showCelebrityDetails(Celebrity celebrity) {
        Details detailsFragment = (Details) fragmentManager.findFragmentById(R.id.flDetails);

        if (detailsFragment == null) {
            detailsFragment = Details.newInstance(celebrity);

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                    .add(R.id.flDetails, detailsFragment, Details.STRING_TAG)
                    .addToBackStack(null)
                    .commit();
        } else {
            detailsFragment.updateData(celebrity);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
