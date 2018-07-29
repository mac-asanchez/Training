package com.example.user.magicleapcoffeeapp.view.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.magicleapcoffeeapp.R;
import com.example.user.magicleapcoffeeapp.di.DaggerMockableComponent;
import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;
import com.example.user.magicleapcoffeeapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.Date;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {
    private static final String TAG = DetailActivity.class.getSimpleName() + "_TAG";

    @Inject
    DetailPresenter presenter;
    String coffeeId;
    private TextView tvDetailName;
    private TextView tvDetailDescription;
    private ImageView ivDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        coffeeId = getIntent().getStringExtra(Constants.COFFEE_ID);

        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        ivDetailImage = findViewById(R.id.ivDetailImage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DaggerMockableComponent.create().injectDetails(this);
        presenter.attachView(this);

        presenter.getCoffeeById(coffeeId);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.detachView();
    }

    @Override
    public void onCoffeeResult(CoffeeResult coffeeResult) {
        Log.d(TAG, "onCoffeeResult: ");
        tvDetailName.setText(coffeeResult.getName());
        tvDetailDescription.setText(coffeeResult.getDesc());

        if (!coffeeResult.getImageUrl().trim().equals(""))
            Picasso.get().load(coffeeResult.getImageUrl()).into(ivDetailImage);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
