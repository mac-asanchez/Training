package com.example.user.githubrest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.user.githubrest.client.RetrofitHelper;
import com.example.user.githubrest.utils.HandlerReposCallBack;
import com.example.user.githubrest.utils.HandlerUtils;

public class RepositoriesActivity extends AppCompatActivity {

    RetrofitHelper retrofitHelper;
    RecyclerView rvRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        rvRepos = findViewById(R.id.rvRepos);

        retrofitHelper = new RetrofitHelper();
        retrofitHelper.getUserReposList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        HandlerUtils.getDefault().setOwner(new HandlerReposCallBack(this, rvRepos));
    }

    @Override
    protected void onStop() {
        super.onStop();
        HandlerUtils.getDefault().unregisterOwner();
    }
}
