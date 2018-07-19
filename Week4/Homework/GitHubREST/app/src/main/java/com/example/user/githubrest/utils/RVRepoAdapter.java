package com.example.user.githubrest.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.githubrest.R;
import com.example.user.githubrest.model.GitHubProfile;
import com.example.user.githubrest.model.GitHubRepo;

import java.util.List;

public class RVRepoAdapter extends RecyclerView.Adapter<RVRepoAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(GitHubRepo item);
    }

    List<GitHubRepo> repos;
    private final OnItemClickListener listener;
    public static final String TAG = RVRepoAdapter.class.getSimpleName() + "_TAG";

    public RVRepoAdapter(List<GitHubRepo> repos, OnItemClickListener listener) {
        this.repos = repos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + holder.toString());
        GitHubRepo repo = repos.get(position);
        holder.tvRepoName.setText(repo.getName());
        holder.tvRepoOwner.setText(repo.getOwner().getLogin());
        holder.tvRepoUrl.setText(repo.getUrl());

        holder.bind(repos.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvRepoName;
        private final TextView tvRepoOwner;
        private final TextView tvRepoUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            tvRepoName = itemView.findViewById(R.id.tvRepoName);
            tvRepoOwner = itemView.findViewById(R.id.tvRepoOwner);
            tvRepoUrl = itemView.findViewById(R.id.tvRepoUrl);

        }

        public void bind(final GitHubRepo repo, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(repo);
                }
            });
        }
    }
}
