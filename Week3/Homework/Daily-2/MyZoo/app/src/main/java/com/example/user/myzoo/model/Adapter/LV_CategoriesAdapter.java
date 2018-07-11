package com.example.user.myzoo.model.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        ImageView ivCategory = convertView.findViewById(R.id.ivCategory);
        //endregion

        //region bind the views with the data
        Category category = getItem(position);
        tvCategoryDescription.setText(category.getCategoryDescription());
        tvCategoryDescription.setTag(category.getCategoryId());

        switch (category.getCategoryId()) {
            case 1:
                ivCategory.setBackgroundResource(R.drawable.mammals);
                break;
            case 2:
                ivCategory.setBackgroundResource(R.drawable.birds);
                break;
            case 3:
                ivCategory.setBackgroundResource(R.drawable.fish);
                break;
            case 4:
                ivCategory.setBackgroundResource(R.drawable.snake);
                break;
            default:
                ivCategory.setBackgroundResource(R.drawable.zoo_wallpaper);
                break;
        }

        //endregion

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
