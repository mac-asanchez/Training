package com.example.user.acronymsapp.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.acronymsapp.R;
import com.example.user.acronymsapp.model.APIResult;
import com.example.user.acronymsapp.model.Lf;

import java.util.List;

public class RVDefinitionAdapter extends RecyclerView.Adapter<RVDefinitionAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Lf item);
    }

    List<Lf> Definitions;
    private final OnItemClickListener listener;
    public static final String TAG = RVDefinitionAdapter.class.getSimpleName() + "_TAG";

    public RVDefinitionAdapter(List<Lf> result, OnItemClickListener listener) {
        this.Definitions = result;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + holder.toString());
        Lf definition = Definitions.get(position);
        holder.tvLf.setText(definition.getLf());
        holder.tvFrequency.setText(String.valueOf(definition.getFreq()));
        holder.tvSince.setText(String.valueOf(definition.getSince()));

        holder.bind(Definitions.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return Definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvLf;
        private final TextView tvFrequency;
        private final TextView tvSince;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            tvLf = itemView.findViewById(R.id.tvLf);
            tvFrequency = itemView.findViewById(R.id.tvFrequency);
            tvSince = itemView.findViewById(R.id.tvSince);

        }

        public void bind(final Lf definition, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(definition);
                }
            });
        }
    }
}
