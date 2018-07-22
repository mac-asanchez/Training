package com.example.user.myzoo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myzoo.model.Data.LocalDataContract;
import com.example.user.myzoo.model.Entity.Animal;

import java.io.IOException;

public class AnimalDetail extends AppCompatActivity {

    MediaPlayer mp;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        populateAnimalDetails();

        prepareMediaPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    private void prepareMediaPlayer() {
        mp = new MediaPlayer();
        //mp.setDataSource(animal.getSoundUrl());
        mp = MediaPlayer.create(this, animal.getSoundUri());
        //mp.prepare();
    }

    private void populateAnimalDetails() {
        Intent intent = this.getIntent();
        animal = intent.getParcelableExtra(LocalDataContract.Table.ANIMAL_NAME);

        TextView tvAnimalName = findViewById(R.id.tvAnimalName);
        TextView tvAnimalCategory = findViewById(R.id.tvAnimalCategory);
        TextView tvAnimalDetail = findViewById(R.id.tvAnimalDetail);

        tvAnimalName.setText(animal.getAnimalDescription());
        tvAnimalCategory.setText(animal.getCategoryDescription());
        tvAnimalDetail.setText(animal.getDetail());
    }

    public void onAnimalSound(View view) {
        //Toast.makeText(this, animal.getSound(), Toast.LENGTH_SHORT).show();
        mp.start();
    }
}
