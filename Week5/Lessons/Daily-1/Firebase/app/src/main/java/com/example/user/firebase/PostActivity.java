package com.example.user.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.user.firebase.utils.Constants;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = PostActivity.class.getSimpleName() + "_TAG";
    private TextView tvPostId;
    private TextView tvPostMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tvPostId = findViewById(R.id.tvPostId);
        tvPostMessage = findViewById(R.id.tvPostMessage);

        String postId = getIntent().getStringExtra(Constants.POST_ID);
        String postMessage = getIntent().getStringExtra(Constants.POST_MESSAGE);

        tvPostId.setText(postId);
        tvPostMessage.setText(postMessage);
    }
}
