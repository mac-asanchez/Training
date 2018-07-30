package com.freelance.ascstb.databinding;

import android.util.Log;
import android.view.View;

public class PersonViewModel {
    private static final String TAG = PersonViewModel.class.getSimpleName() + "_TAG";

    public void onPersonClicked(View view, Person person) {
        Log.d(TAG, "onPersonClicked: " + person.toString());
    }
}
