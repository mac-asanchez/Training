package com.example.user.containers.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.containers.R;
import com.example.user.containers.model.Book;

import java.util.List;

public class RVBookAdapter extends RecyclerView.Adapter<RVBookAdapter.ViewHolder> {
    List<Book> books;
    public static final String TAG = RVBookAdapter.class.getSimpleName() + "_TAG";

    public RVBookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getItemViewType(int position) {
        Book book = books.get(position);
        if (book.getISBN().equals("BOOK-3")) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        int currentLayout;

        if (viewType == 1) {
            currentLayout = R.layout.book_list_item2;
        } else {
            currentLayout = R.layout.book_list_item;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(currentLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + holder.toString());
        Book book = books.get(position);
        holder.tvBookISBN.setText(book.getISBN());
        holder.tvBookName.setText(book.getName());
        holder.tvBookAuthor.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvBookISBN;
        private final TextView tvBookName;
        private final TextView tvBookAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //region initialize the views
            tvBookISBN = itemView.findViewById(R.id.tvISBN);
            tvBookName = itemView.findViewById(R.id.tvName);
            tvBookAuthor = itemView.findViewById(R.id.tvAuthor);
            //endregion
        }
    }
}
