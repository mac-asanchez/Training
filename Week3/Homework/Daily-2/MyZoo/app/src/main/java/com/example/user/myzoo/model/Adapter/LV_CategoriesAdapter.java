package com.example.user.myzoo.model.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.myzoo.R;
import com.example.user.myzoo.model.Entity.Category;

import java.util.List;

public class LV_CategoriesAdapter extends ArrayAdapter<Category> {
    List<Category> Categories;

    public LV_CategoriesAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, null);
        }

        //region initialize the views
        TextView tvCategoryDescription = convertView.findViewById(R.id.tvCategoryDescription);
        //endregion

        //region bind the views with the data
        Category category = getItem(position);
        tvCategoryDescription.setText(category.getCategoryDescription());
        tvCategoryDescription.setTag(category.getCategoryId());
        //endregion

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
