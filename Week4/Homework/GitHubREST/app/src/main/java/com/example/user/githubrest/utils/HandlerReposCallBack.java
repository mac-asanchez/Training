package com.example.user.githubrest.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.githubrest.model.GitHubProfile;
import com.example.user.githubrest.model.GitHubRepo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HandlerReposCallBack implements Handler.Callback {
    public static final String TAG = HandlerReposCallBack.class.getSimpleName() + "_TAG";
    Context context;
    RecyclerView rvRepos;

    public HandlerReposCallBack(Context context, RecyclerView rvRepos) {
        this.context = context;
        this.rvRepos = rvRepos;
    }

    @Override
    public boolean handleMessage(Message message) {
        Log.d(TAG, "handleMessage: ");
        List<GitHubRepo> gitHubRepos = HandlerUtils.getDefault().parseListMessage(message);
        Log.d(TAG, "handleMessage: gitHubRepos: " + gitHubRepos.size());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        Log.d(TAG, "handleMessage: layoutManager");
        rvRepos.setLayoutManager(layoutManager);
        Log.d(TAG, "handleMessage: setlayoutManager");
        rvRepos.setAdapter(new RVRepoAdapter(gitHubRepos, new RVRepoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GitHubRepo item) {

            }
        }));

        Log.d(TAG, "handleMessage: end");
        return false;
    }
}
