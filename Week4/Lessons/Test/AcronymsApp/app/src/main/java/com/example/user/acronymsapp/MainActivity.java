package com.example.user.acronymsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.user.acronymsapp.client.RetrofitHelper;
import com.example.user.acronymsapp.utils.HandlerCallback;
import com.example.user.acronymsapp.utils.HandlerUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private EditText etAcronym;
    private RecyclerView rvDefinitions;
    RetrofitHelper retrofitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAcronym = findViewById(R.id.etAcronym);
        rvDefinitions = findViewById(R.id.rvDefinitions);

        retrofitHelper = new RetrofitHelper();
    }

    @Override
    protected void onStart() {
        super.onStart();
        HandlerUtils.getDefault().setOwner(new HandlerCallback(this, rvDefinitions));
    }

    @Override
    protected void onStop() {
        super.onStop();
        HandlerUtils.getDefault().unregisterOwner();
    }

    public void onCallAPI(View view) {
        Log.d(TAG, "onCallAPI: ");
        retrofitHelper.getDefinitionsList(etAcronym.getText().toString());
    }
}
