package com.example.user.storelocator.model.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.storelocator.R;
import com.example.user.storelocator.model.dominos.Store;

import java.util.List;

public class RVStoreAdapter extends RecyclerView.Adapter<RVStoreAdapter.ViewHolder> {
    List<Store> storeList;

    public RVStoreAdapter(List<Store> storeList) {
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        int currentLayout = R.layout.store_list_item;
        View v = LayoutInflater.from(parent.getContext()).inflate(currentLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = storeList.get(position);

        holder.tvAddress.setText(store.getAddressDescription());
        holder.tvHours.setText(store.getHoursDescription());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAddress;
        TextView tvHours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvHours = itemView.findViewById(R.id.tvHours);
        }
    }
}
