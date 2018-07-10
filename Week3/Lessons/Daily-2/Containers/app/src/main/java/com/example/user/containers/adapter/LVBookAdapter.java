package com.example.user.containers.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.containers.R;
import com.example.user.containers.model.Book;

import java.util.List;

import static com.example.user.containers.R.layout.book_list_item;

public class LVBookAdapter extends ArrayAdapter<Book> {
    List<Book> books;

    public LVBookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, null);
        }

        //region initialize the views
        TextView tvBookISBN = convertView.findViewById(R.id.tvISBN);
        TextView tvBookName = convertView.findViewById(R.id.tvName);
        TextView tvBookAuthor = convertView.findViewById(R.id.tvAuthor);
        //endregion

        //region bind the views with the data
        Book book = getItem(position);
        tvBookISBN.setText(book.getISBN());
        tvBookName.setText(book.getName());
        tvBookAuthor.setText(book.getAuthor());
        //endregion

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
