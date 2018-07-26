package com.example.user.recepiesapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.recepiesapp.R;
import com.example.user.recepiesapp.model.edamam.Hit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private static final String TAG = RVAdapter.class.getSimpleName() + "_TAG";
    List<Hit> recipes;

    public RVAdapter(List<Hit> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.d(TAG, "onCreateViewHolder: ");
        int currentLayout = R.layout.recipe_list_item;

        View view = LayoutInflater.from(parent.getContext()).inflate(currentLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hit recipe = recipes.get(position);
        Picasso.get().load(recipe.getRecipe().getImage()).into(holder.ivRecipe);
        holder.ivRecipe.setTag(position);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRecipe = itemView.findViewById(R.id.ivRecipe);
        }
    }
}
