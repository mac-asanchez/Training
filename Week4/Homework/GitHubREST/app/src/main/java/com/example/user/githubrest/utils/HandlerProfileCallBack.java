package com.example.user.githubrest.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.githubrest.model.GitHubProfile;
import com.squareup.picasso.Picasso;

public class HandlerProfileCallBack implements Handler.Callback {
    Context context;
    ImageView ivProfilePicture;
    TextView tvLogin;
    TextView tvName;
    TextView tvUrl;
    TextView tvBio;

    public HandlerProfileCallBack(Context context, ImageView ivProfilePicture, TextView tvLogin, TextView tvName, TextView tvUrl, TextView tvBio) {
        this.context = context;
        this.ivProfilePicture = ivProfilePicture;
        this.tvLogin = tvLogin;
        this.tvName = tvName;
        this.tvUrl = tvUrl;
        this.tvBio = tvBio;
    }

    @Override
    public boolean handleMessage(Message message) {
        GitHubProfile gitHubProfile = (GitHubProfile) HandlerUtils.getDefault().parseMessage(message);
        /*Toast.makeText(context, gitHubProfile.getLogin(), Toast.LENGTH_SHORT).show();*/

        try {
            Picasso.get().load(gitHubProfile.getAvatarUrl()).into(ivProfilePicture);
            tvLogin.setText(gitHubProfile.getLogin());
            tvName.setText(gitHubProfile.getName().toString());
            tvUrl.setText(gitHubProfile.getHtmlUrl());
            tvBio.setText(gitHubProfile.getBio().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
