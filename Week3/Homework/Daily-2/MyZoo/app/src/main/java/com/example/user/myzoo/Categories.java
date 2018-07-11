package com.example.user.myzoo;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.myzoo.model.Adapter.LV_CategoriesAdapter;
import com.example.user.myzoo.model.Data.LocalDataContract;
import com.example.user.myzoo.model.Data.LocalDataSource;
import com.example.user.myzoo.model.Entity.Category;

import java.util.List;

public class Categories extends AppCompatActivity {

    private LocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        populateCategories();
    }

    private void populateCategories() {
        localDataSource = new LocalDataSource(this);
        List<Category> categoryList = localDataSource.getAllCategories();
        LV_CategoriesAdapter adapter = new LV_CategoriesAdapter(this, R.layout.category_list_item, categoryList);
        ListView lvCategories = findViewById(R.id.lvCategories);
        lvCategories.setAdapter(adapter);
    }

    public void onCategorySelected(View view) {
        Intent intent = new Intent(this, Animals.class);
        int CategoryId = Integer.parseInt(view.getTag().toString());

        if (CategoryId > 0) {
            intent.putExtra(LocalDataContract.Category.ID, CategoryId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Not a valid category", Toast.LENGTH_SHORT).show();
        }
    }
}
