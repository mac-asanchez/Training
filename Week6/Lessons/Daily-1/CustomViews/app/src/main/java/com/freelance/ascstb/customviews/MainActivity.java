package com.freelance.ascstb.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.freelance.ascstb.customviews.view.MyCircleView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MyCircleView myCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCircleView = findViewById(R.id.myCircleView);
    }

    public void updateCircleRadius(View view) {
        myCircleView.setRadius(new Random().nextInt(200));
    }

    public void updateCircleColor(View view) {
    }
}
