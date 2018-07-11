package com.example.user.myzoo;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.myzoo.model.Adapter.RV_AnimalsAdapter;
import com.example.user.myzoo.model.Data.LocalDataContract;
import com.example.user.myzoo.model.Data.LocalDataSource;
import com.example.user.myzoo.model.Entity.Animal;

import java.util.List;

public class Animals extends AppCompatActivity {

    private LocalDataSource localDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        populateAnimals();
    }

    private void populateAnimals() {
        Intent intentCategoryId = this.getIntent();
        int CategoryId = intentCategoryId.getIntExtra(LocalDataContract.Category.ID, 0);
        localDataSource = new LocalDataSource(this);
        List<Animal> animalList = localDataSource.getAnimalsByCategory(CategoryId);

        RecyclerView rvAnimals = findViewById(R.id.rvAnimals);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAnimals.setLayoutManager(layoutManager);
        rvAnimals.setAdapter(new RV_AnimalsAdapter(animalList, new RV_AnimalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Animal animal) {
                //Toast.makeText(Animals.this, animal.toString(), Toast.LENGTH_SHORT).show();
                Intent intentDetails = new Intent(Animals.this, AnimalDetail.class);
                intentDetails.putExtra(LocalDataContract.Table.ANIMAL_NAME, (Parcelable) animal);
                startActivity(intentDetails);
            }
        }));
    }
}
