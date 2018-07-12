package com.example.user.celebfrags.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.celebfrags.Celebrities;
import com.example.user.celebfrags.R;
import com.example.user.celebfrags.model.Celebrity;

import java.util.List;

public class LVCelebritiesAdapter extends ArrayAdapter<Celebrity> {
    List<Celebrity> Categories;

    public LVCelebritiesAdapter(@NonNull Context context, int resource, @NonNull List<Celebrity> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.celebrity_list_item, null);
        }

        //region initialize the views
        TextView tvCelebrityName = convertView.findViewById(R.id.tvCelebrityName);
        //endregion

        //region bind the views with the data
        Celebrity celebrity = getItem(position);
        tvCelebrityName.setText(celebrity.getName());
        tvCelebrityName.setTag(celebrity.getCelebrityId());
        //endregion

        return convertView;
    }
}
