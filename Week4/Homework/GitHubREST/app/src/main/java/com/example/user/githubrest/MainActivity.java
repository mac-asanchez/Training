package com.example.user.githubrest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.githubrest.client.RetrofitHelper;
import com.example.user.githubrest.utils.HandlerProfileCallBack;
import com.example.user.githubrest.utils.HandlerUtils;

public class MainActivity extends AppCompatActivity {

    RetrofitHelper retrofitHelper;
    private ImageView ivProfilePicture;
    private TextView tvLogin;
    private TextView tvName;
    private TextView tvUrl;
    private TextView tvBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getControls();

        callInfoFromGitHub();
    }

    private void getControls() {
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        tvLogin = findViewById(R.id.tvLogin);
        tvName = findViewById(R.id.tvName);
        tvUrl = findViewById(R.id.tvUrl);
        tvBio = findViewById(R.id.tvBio);
    }

    private void callInfoFromGitHub() {
        retrofitHelper = new RetrofitHelper();
        retrofitHelper.getUserProfileInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        HandlerUtils.getDefault().setOwner(new HandlerProfileCallBack(this, ivProfilePicture, tvLogin, tvName, tvUrl, tvBio));
    }

    @Override
    protected void onStop() {
        super.onStop();
        HandlerUtils.getDefault().unregisterOwner();
    }

    public void onRepositoriesClicked(View view) {
        Intent intent = new Intent(this, RepositoriesActivity.class);
        startActivity(intent);
    }
}
