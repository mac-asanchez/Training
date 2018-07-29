package com.example.user.magicleapcoffeeapp.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.magicleapcoffeeapp.R;
import com.example.user.magicleapcoffeeapp.model.mockable.CoffeeResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private static final String TAG = RVAdapter.class.getSimpleName() + "_TAG";
    List<CoffeeResult> coffeeResultList;

    public RVAdapter(List<CoffeeResult> coffeeResultList) {
        this.coffeeResultList = coffeeResultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        int currentLayout = R.layout.coffee_list_item;

        View view = LayoutInflater.from(parent.getContext()).inflate(currentLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoffeeResult coffee = coffeeResultList.get(position);

        holder.tvName.setText(coffee.getName());
        holder.tvName.setTag(coffee.getId());

        holder.tvDescription.setText(coffee.getDesc());
        holder.tvDescription.setTag(coffee.getId());

        if (!coffee.getImageUrl().trim().equals(""))
            Picasso.get().load(coffee.getImageUrl()).into(holder.ivCoffeeImage);

        holder.ivCoffeeImage.setTag(coffee.getId());
    }

    @Override
    public int getItemCount() {
        return coffeeResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        ImageView ivCoffeeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivCoffeeImage = itemView.findViewById(R.id.ivCoffeeImage);
        }
    }
}
