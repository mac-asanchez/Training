package com.example.user.recepiesapp.view.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.recepiesapp.R;
import com.example.user.recepiesapp.model.edamam.Hit;
import com.example.user.recepiesapp.model.edamam.Ingredient;
import com.example.user.recepiesapp.model.edamam.Recipe;
import com.example.user.recepiesapp.utils.Constants;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName() + "_TAG";
    private ImageView ivRecipeImage;
    private TextView tvRecipeName;
    private TextView tvIngredients;
    private TextView tvSource;
    private TextView tvSourceLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getControls();

        showRecipe();
    }

    private void getControls() {
        ivRecipeImage = findViewById(R.id.ivRecipeImage);
        tvRecipeName = findViewById(R.id.tvRecipeName);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvSource = findViewById(R.id.tvSource);
        tvSourceLink = findViewById(R.id.tvSourceLink);
    }

    private void showRecipe() {
        Recipe recipe = getIntent().getParcelableExtra(Constants.RECIPE_DETAIL);
        String ingredients = "";
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredients += "• " + ingredient.getText() + "\n";
        }

        Picasso.get().load(recipe.getImage()).into(ivRecipeImage);
        tvRecipeName.setText(recipe.getLabel());
        tvIngredients.setText(ingredients);
        tvSource.setText(recipe.getSource());
        tvSourceLink.setText(recipe.getUrl());
    }
}
