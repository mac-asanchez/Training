package com.example.user.myzoo.model.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myzoo.R;
import com.example.user.myzoo.model.Constants;
import com.example.user.myzoo.model.Entity.Animal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RV_AnimalsAdapter extends RecyclerView.Adapter<RV_AnimalsAdapter.ViewHolder> {
    public static final String TAG = RV_AnimalsAdapter.class.getSimpleName() + "_TAG";
    private final OnItemClickListener listener;
    List<Animal> animals;

    public RV_AnimalsAdapter(List<Animal> animals, OnItemClickListener listener) {
        this.animals = animals;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Animal animal = animals.get(position);
        return (animal.getCategoryId() == 2) ? Constants.SecondLayout : Constants.FirstLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        int CurrentLayout = (viewType == Constants.FirstLayout) ? R.layout.animal_list_item : R.layout.animal_list_item2;

        View view = LayoutInflater.from(parent.getContext()).inflate(CurrentLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + holder.toString());
        Animal animal = animals.get(position);
        holder.tvAnimalDescription.setText(animal.getAnimalDescription());
        holder.tvAnimalDescription.setTag(String.valueOf(animal.getAnimalId()));
        if (holder.tvAnimalDetail != null) {
            holder.tvAnimalDetail.setText(animal.getDetail());
        }

        Picasso.get().load(animal.getImageUrl()).into(holder.ivAnimal);

        holder.bind(animals.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Animal item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAnimal;
        private final TextView tvAnimalDescription;
        private final TextView tvAnimalDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //region initialize the views
            tvAnimalDescription = itemView.findViewById(R.id.tvAnimalDescription);
            ivAnimal = itemView.findViewById(R.id.ivAnimal);
            tvAnimalDetail = itemView.findViewById(R.id.tvAnimalDetail);
            //endregion
        }

        public void bind(final Animal animal, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(animal);
                }
            });
        }
    }
}
