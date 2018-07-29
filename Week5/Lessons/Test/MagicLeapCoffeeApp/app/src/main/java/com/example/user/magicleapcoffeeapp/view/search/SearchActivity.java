package com.example.user.magicleapcoffeeapp.view.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.magicleapcoffeeapp.R;
import com.example.user.magicleapcoffeeapp.model.adapter.RVAdapter;
import com.example.user.magicleapcoffeeapp.di.DaggerMockableComponent;
import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;
import com.example.user.magicleapcoffeeapp.utils.Constants;
import com.example.user.magicleapcoffeeapp.view.detail.DetailActivity;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private static final String TAG = SearchActivity.class.getSimpleName() + "_TAG";

    @Inject
    SearchPresenter presenter;
    private RecyclerView rvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvResults = findViewById(R.id.rvResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DaggerMockableComponent.create().inject(this);
        presenter.attachView(this);

        presenter.getCoffees();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onCoffeesResult(List<CoffeeResult> coffeeResultList) {
        Log.d(TAG, "onCoffeesResult: " + coffeeResultList.size());

        //Load results into gridview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RVAdapter adapter = new RVAdapter(coffeeResultList);
        rvResults.setLayoutManager(layoutManager);
        rvResults.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void onCoffeeSelected(View view) {
        String CoffeeId = view.getTag().toString();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(Constants.COFFEE_ID, CoffeeId);
        startActivity(intent);
    }
}
