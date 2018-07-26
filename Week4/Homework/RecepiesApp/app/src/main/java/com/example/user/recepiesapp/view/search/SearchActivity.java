package com.example.user.recepiesapp.view.search;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.recepiesapp.R;
import com.example.user.recepiesapp.adapter.RVAdapter;
import com.example.user.recepiesapp.di.DaggerEdamamComponent;
import com.example.user.recepiesapp.model.edamam.Hit;
import com.example.user.recepiesapp.model.edamam.Recipe;
import com.example.user.recepiesapp.utils.Constants;
import com.example.user.recepiesapp.view.detail.DetailActivity;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private static final String TAG = SearchActivity.class.getSimpleName() + "_TAG";
    private EditText etSearchBox;

    @Inject
    SearchPresenter presenter;
    private RecyclerView rvResults;
    private List<Hit> recepies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearchBox = findViewById(R.id.etSearchBox);
        rvResults = findViewById(R.id.rvResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DaggerEdamamComponent.create().inject(this);
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onRecipesResult(List<Hit> recipeList) {
        Log.d(TAG, "onRecipesResult: " + recipeList.size());
        recepies = recipeList;

        //Load results into gridview
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        RVAdapter adapter = new RVAdapter(recipeList);
        rvResults.setLayoutManager(layoutManager);
        rvResults.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void onSearchRequest(View view) {
        Log.d(TAG, "onSearchRequest: ");
        Toast.makeText(this, "Loading, Please wait", Toast.LENGTH_LONG).show();
        presenter.getRecipes(etSearchBox.getText().toString());
    }

    public void onRecipeSelected(View view) {
        ImageView ivRecipe = (ImageView) view;
        int position = Integer.parseInt(ivRecipe.getTag().toString());
        Recipe recipe = recepies.get(position).getRecipe();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(Constants.RECIPE_DETAIL, recipe);
        startActivity(intent);
    }
}
