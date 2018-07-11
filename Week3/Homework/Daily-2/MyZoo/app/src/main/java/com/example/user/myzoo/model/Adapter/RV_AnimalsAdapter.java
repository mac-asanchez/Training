package com.example.user.myzoo.model.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.user.myzoo.R;
import com.example.user.myzoo.model.Entity.Animal;

import java.util.List;

public class RV_AnimalsAdapter extends RecyclerView.Adapter<RV_AnimalsAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Animal item);
    }

    List<Animal> animals;
    private final OnItemClickListener listener;
    public static final String TAG = RV_AnimalsAdapter.class.getSimpleName() + "_TAG";

    public RV_AnimalsAdapter(List<Animal> animals, OnItemClickListener listener) {
        this.animals = animals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + holder.toString());
        Animal animal = animals.get(position);
        holder.tvAnimalDescription.setText(animal.getAnimalDescription());
        holder.tvAnimalDescription.setTag(animal.getAnimalId());
        holder.bind(animals.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAnimalDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //region initialize the views
            tvAnimalDescription = itemView.findViewById(R.id.tvAnimalDescription);
            //endregion
        }

        public void bind(final Animal animal, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(animal);
                }
            });
        }
    }
}
