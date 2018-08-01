package com.example.user.customviewlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.user.customviewlogin.custom.MyButton;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName() + "_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: ");

        ImageView ivImage = findViewById(R.id.ivHomeImage);
        MyButton myButton = findViewById(R.id.btnCustom);

        String ImageUrl = "https://img.memecdn.com/open-the-jar_o_973817.jpg";
        myButton.setImage(ImageUrl, this);
    }
}
